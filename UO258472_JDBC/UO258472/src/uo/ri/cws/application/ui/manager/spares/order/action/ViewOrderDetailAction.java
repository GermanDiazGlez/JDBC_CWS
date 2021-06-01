package uo.ri.cws.application.ui.manager.spares.order.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrdersService;
import uo.ri.cws.application.ui.util.Printer;

public class ViewOrderDetailAction implements Action {

	@Override
	public void execute() throws Exception {
		try{
			String orderCode = Console.readString("Order code: ");

			OrdersService service = BusinessFactory.forOrdersService();
			Optional<OrderDto> oo = service.findByCode( orderCode );

			if ( oo.isEmpty()) {
				Console.println("There is no order with such code");
				return;
			}

			Printer.printDetail( oo.get() );
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}

}
