package uo.ri.cws.application.ui.manager.spares.order.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrdersService;
import uo.ri.cws.application.ui.util.Printer;

public class GenerateOrdersAction implements Action {

	@Override
	public void execute() throws Exception {
		Console.println("New orders are about to be generated");
		
		OrdersService service = BusinessFactory.forOrdersService();
		
		List<OrderDto> orders = service.generateOrders();
		Console.println( orders.size() + " have been generated.");
		for(OrderDto order: orders) {
			Printer.printSummary(order);
		}

	}

}
