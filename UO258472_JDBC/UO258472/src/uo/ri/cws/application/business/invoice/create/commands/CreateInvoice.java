package uo.ri.cws.application.business.invoice.create.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import alb.util.assertion.Argument;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;

public class CreateInvoice {
	private List<String> workOrderIds;
	
	InvoiceRecord invoice = new InvoiceRecord();
	
	private static final String SQL_CHECK_WORKORDER_STATUS = 
			"select status from TWorkOrders where id = ?";

	private static final String SQL_LAST_INVOICE_NUMBER = 
			"select max(number) from TInvoices";

	private static final String SQL_FIND_WORKORDER_AMOUNT = 
			"select amount from TWorkOrders where id = ?";

	private static final String SQL_LINK_WORKORDER_TO_INVOICE = 
			"update TWorkOrders set invoice_id = ? where id = ?";

	private static final String SQL_MARK_WORKORDER_AS_INVOICED = 
			"update TWorkOrders set status = 'INVOICED' where id = ?";

	private static final String SQL_FIND_WORKORDERS = 
			"select * from TWorkOrders where id = ?";

	private Connection connection;	

	public CreateInvoice(List<String> workOrderIds) {
		Argument.isNotNull(workOrderIds,"The workOrderIds cant be null");
		for (String work: workOrderIds) {
			Argument.isTrue(work.trim().length()!=0, "The workOrderIds cant be a blank");
		}
		this.workOrderIds = workOrderIds;
	}


	public InvoiceDto execute() {
		InvoiceDto invoiceDto = new InvoiceDto();
		try {
			connection = Jdbc.getConnection();

			if (! checkWorkOrdersExist(workOrderIds) )
				throw new BusinessException ("Workorder does not exist");
			if (! checkWorkOrdersFinished(workOrderIds) )
				throw new BusinessException ("Workorder is not finished yet");


			long numberInvoice = generateInvoiceNumber();
			LocalDate dateInvoice = LocalDate.now();
			double amount = calculateTotalInvoice(workOrderIds); // vat not included
			double vat = vatPercentage(amount, dateInvoice);
			double total = amount * (1 + vat/100); // vat included
			total = Round.twoCents(total);
				
			InvoiceRecord invoice = new InvoiceRecord();
			invoice.number=numberInvoice;
			invoice.date=dateInvoice;
			invoice.vat=vat;
			invoice.total=total;
			
			//llamar al add de invoice gateway que devuelve void
			
			String idInvoice = createInvoice(invoice);
			linkWorkordersToInvoice(idInvoice, workOrderIds);
			markWorkOrderAsInvoiced(workOrderIds);

			displayInvoice(numberInvoice, dateInvoice, amount, vat, total);
			invoice.id = idInvoice;
			invoice.total = total;
			invoice.vat = vat;
			invoice.number = numberInvoice;
			invoice.date = dateInvoice;

			invoiceDto = DtoMapper.toDto(invoice);
			
			connection.commit();
		}
		catch (BusinessException e) {
			System.out.println(e.getMessage());
		} catch (SQLException sql){
			System.out.println(sql.getMessage());
		}
		finally {
			Jdbc.close(connection);
		}
		return invoiceDto;
	}


	/*
	 * checks whether every work order exist	 
	 */
	private boolean checkWorkOrdersExist(List<String> workOrderIDS) throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_FIND_WORKORDERS);

			for (String workOrderID : workOrderIDS) {
				pst.setString(1, workOrderID);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					return false;
				}

			}
		} finally {
			Jdbc.close(rs, pst);
		}
		return true;
	}

	/*
	 * checks whether every work order id is FINISHED	 
	 */
	private boolean checkWorkOrdersFinished(List<String> workOrderIDS) throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_CHECK_WORKORDER_STATUS);

			for (String workOrderID : workOrderIDS) {
				pst.setString(1, workOrderID);

				rs = pst.executeQuery();
				rs.next();
				String status = rs.getString(1); 
				if (! "FINISHED".equalsIgnoreCase(status) ) {
					return false;
				}

			}
		} finally {
			Jdbc.close(rs, pst);
		}
		return true;
	}

	/*
	 * Generates next invoice number (not to be confused with the inner id)
	 */
	private Long generateInvoiceNumber() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_LAST_INVOICE_NUMBER);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, next
			} else { // there is none yet
				return 1L;
			}
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	/*
	 * Compute total amount of the invoice  (as the total of individual work orders' amount 
	 */
	private double calculateTotalInvoice(List<String> workOrderIDS) throws BusinessException, SQLException {

		double totalInvoice = 0.0;
		for (String workOrderID : workOrderIDS) {
			totalInvoice += getWorkOrderTotal(workOrderID);
		}
		return totalInvoice;
	}

	/*
	 * checks whether every work order id is FINISHED	 
	 */
	private Double getWorkOrderTotal(String workOrderID) throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Double money = 0.0;

		try {
			pst = connection.prepareStatement(SQL_FIND_WORKORDER_AMOUNT);
			pst.setString(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("Workorder " + workOrderID + " doesn't exist");
			}

			money = rs.getDouble(1); 

		} finally {
			Jdbc.close(rs, pst);
		}
		return money;

	}

	/*
	 * returns vat percentage 
	 */
	private double vatPercentage(double totalInvoice, LocalDate dateInvoice) {
		return LocalDate.parse("2012-07-01").isBefore(dateInvoice) ? 21.0 : 18.0;

	}

	/*
	 * Creates the invoice in the database; returns the id
	 */
	private String createInvoice(InvoiceRecord invoice) throws SQLException {

		InvoiceGateway ig = PersistenceFactory.forInvoice();
		ig.add(invoice);
		
		return invoice.id; 
	}

	/*
	 * Set the invoice number field in work order table to the invoice number generated
	 */
	private void linkWorkordersToInvoice (String invoiceId, List<String> workOrderIDS) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(SQL_LINK_WORKORDER_TO_INVOICE);

			for (String breakdownId : workOrderIDS) {
				pst.setString(1, invoiceId);
				pst.setString(2, breakdownId);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	/*
	 * Sets status to INVOICED for every workorder
	 */
	private void markWorkOrderAsInvoiced(List<String> ids) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(SQL_MARK_WORKORDER_AS_INVOICED);

			for (String id: ids) {
				pst.setString(1, id);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}


	private void displayInvoice(long numberInvoice, LocalDate dateInvoice,
			double totalInvoice, double vat, double totalConIva) {

		Console.printf("Invoice number: %d\n", numberInvoice);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", dateInvoice);
		Console.printf("\tAmount: %.2f €\n", totalInvoice);
		Console.printf("\tVAT: %.1f %% \n", vat);
		Console.printf("\tTotal (including VAT): %.2f €\n", totalConIva);
	}

}
