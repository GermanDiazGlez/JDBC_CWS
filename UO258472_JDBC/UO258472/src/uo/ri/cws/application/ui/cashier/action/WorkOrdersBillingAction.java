package uo.ri.cws.application.ui.cashier.action;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoicingService;

public class WorkOrdersBillingAction implements Action {



	@Override
	public void execute() throws BusinessException {
		List<String> workOrderIds = new ArrayList<String>();
		try{
			// type work order ids to be invoiced in the invoice
			do {
				String id = Console.readString("Type work order ids:  ");

				workOrderIds.add(id);
			} while ( nextWorkorder() );
			InvoicingService is = BusinessFactory.forCreateInvoiceService();
			InvoiceDto invoice = is.createInvoiceFor(workOrderIds);

			double totalNotIva = invoice.total / (1 + invoice.vat / 100); // vat included

			displayInvoice(invoice.number, invoice.date, totalNotIva, invoice.vat, invoice.total);
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}


	/*
	 * read work order ids to invoice
	 */
	private boolean nextWorkorder() {
		return Console.readString(" Any other workorder? (y/n) ").equalsIgnoreCase("y");
	}

	private void displayInvoice(long numberInvoice, LocalDate dateInvoice, double totalInvoice, double vat,
								double totalConIva) {

		Console.printf("Invoice number: %d\n", numberInvoice);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", dateInvoice);
		Console.printf("\tAmount: %.2f €\n", totalInvoice);
		Console.printf("\tVAT: %.1f %% \n", vat);
		Console.printf("\tTotal (including VAT): %.2f €\n", totalConIva);
	}

	
}
