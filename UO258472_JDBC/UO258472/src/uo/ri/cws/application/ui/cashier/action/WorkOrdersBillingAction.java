package uo.ri.cws.application.ui.cashier.action;

import java.util.ArrayList;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
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
			is.createInvoiceFor(workOrderIds);
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

	
}
