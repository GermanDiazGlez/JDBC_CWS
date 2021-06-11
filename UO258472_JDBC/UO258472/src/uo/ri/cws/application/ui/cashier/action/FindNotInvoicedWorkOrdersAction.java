package uo.ri.cws.application.ui.cashier.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.ui.util.Printer;

public class FindNotInvoicedWorkOrdersAction implements Action {


	/**
	 * Process:
	 *
	 *   - Ask customer dni
	 *
	 *   - Display all uncharged workorder
	 *   		(status <> 'INVOICED'). For each workorder, display
	 *   		id, vehicle id, date, status, amount and description
	 */
	@Override
	public void execute() throws BusinessException {
		InvoicingService is = BusinessFactory.forCreateInvoiceService();
		String dni = Console.readString("Client DNI ");
		
		Console.println("\nClient's not invoiced work orders\n");

		List<InvoicingWorkOrderDto> workOrders = is.findWorkOrdersByClientDni(dni);

		Printer.printInvoices(workOrders);
	}

}