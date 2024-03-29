package uo.ri.cws.application.business.invoice.create.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import alb.util.assertion.Argument;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import jdk.swing.interop.SwingInterOpUtils;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class CreateInvoice implements Command<InvoiceDto> {
	private List<String> workOrderIds;
	
	private InvoiceGateway invoiceGateway = PersistenceFactory.forInvoice();
	private WorkOrderGateway workOrderGateway = PersistenceFactory.forWorkOrder();


	public CreateInvoice(List<String> workOrderIds) {
		Argument.isNotNull(workOrderIds,"The workOrderIds cant be null");
		Argument.isTrue(!workOrderIds.isEmpty(),"Work order ids cant be empty");
		for (String work: workOrderIds) {
			Argument.isTrue(work.trim().length()!=0, "The workOrderIds cant be a blank");
		}
		this.workOrderIds = workOrderIds;
	}


	public InvoiceDto execute() throws BusinessException, SQLException {
		if (! checkWorkOrdersExist(workOrderIds) )
			throw new BusinessException ("Workorder does not exist");
		if (! checkWorkOrdersFinished(workOrderIds) )
			throw new BusinessException ("Workorder is not finished yet");

		long numberInvoice = invoiceGateway.getNextInvoiceNumber();
		LocalDate dateInvoice = LocalDate.now();
		double amount = calculateTotalInvoice(workOrderIds); // vat not included
		double vat = vatPercentage(amount, dateInvoice);
		double total = amount * (1 + vat/100); // vat included
		total = Round.twoCents(total);
		String status = "NOT_YET_PAID";

		InvoiceRecord invoice = new InvoiceRecord();
		invoice.number=numberInvoice;
		invoice.date=dateInvoice;
		invoice.vat=vat;
		invoice.total=total;
		invoice.status=status;
		invoice.id = UUID.randomUUID().toString();

		createInvoice(invoice);

		linkWorkordersToInvoice(invoice.id, workOrderIds);
		markWorkOrderAsInvoiced(workOrderIds);

		return DtoMapper.toDto(invoice);
	}


	/*
	 * checks whether every work order exist	 
	 */
	private boolean checkWorkOrdersExist(List<String> workOrderIDS) throws SQLException, BusinessException {
		for (String str : workOrderIds) {
			if (str == null || str.trim().isEmpty() || !workOrderGateway.findById(str).isPresent())
				return false;
		}
		return true;
	}

	/*
	 * checks whether every work order id is FINISHED	 
	 */
	private boolean checkWorkOrdersFinished(List<String> workOrderIDS) throws SQLException, BusinessException {
		for (String workOrderID : workOrderIDS) {
			if (!workOrderID.equals(null) && !workOrderID.trim().equals("")) {
				if (workOrderGateway.findById(workOrderID).isPresent()) {
					String status = workOrderGateway.findById(workOrderID).get().status;
					if (status.equals("FINISHED")) {
						return true;
					}
				}
			}
		}
		return false;
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
		Double money = 0.0;

		if (!workOrderGateway.findById(workOrderID).isPresent())
			throw new BusinessException("Workorder " + workOrderID + " doesn't exist");

		money = workOrderGateway.findById(workOrderID).get().total;

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
	private void createInvoice(InvoiceRecord invoice) throws SQLException {
		invoiceGateway.add(invoice);
	}

	/*
	 * Set the invoice number field in work order table to the invoice number generated
	 */
	private void linkWorkordersToInvoice (String invoiceId, List<String> workOrderIDS) throws SQLException {

		WorkOrderRecord record;

		for (String workOrderId : workOrderIDS) {
			record = new WorkOrderRecord();
			record.id = workOrderId;
			record.invoiceId = invoiceId;

			workOrderGateway.update(record);
		}
	}

	/*
	 * Sets status to INVOICED for every workorder
	 */
	private void markWorkOrderAsInvoiced(List<String> ids) throws SQLException {

		WorkOrderRecord record;

		for (String id : ids) {
			record = new WorkOrderRecord();
			record.id = id;
			record.status = "INVOICED";

			workOrderGateway.update(record);
		}
	}

}
