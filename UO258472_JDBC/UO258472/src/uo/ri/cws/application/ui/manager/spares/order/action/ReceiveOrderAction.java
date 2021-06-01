package uo.ri.cws.application.ui.manager.spares.order.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrdersService;
import uo.ri.cws.application.ui.util.Printer;

public class ReceiveOrderAction implements Action {

	@Override
	public void execute() throws Exception {
		try{
			String code = Console.readString("Please, type the order code: ");

			OrdersService service = BusinessFactory.forOrdersService();
			Optional<OrderDto> order = service.findByCode( code );

			if ( order.isEmpty()) {
				Console.println("There is no order with such code");
				return;
			}

			Console.println("Please, review the content of the order received");
			Printer.printDetail( order.get() );

			String yesNo = Console.readString("Is all right? (y/n)");
			if ( yesNo.equalsIgnoreCase( "y" ) ) {
				service.receive(code);
				Console.println("The reception has been registered and the prices updated");
			} else {
				Console.println("Please, contact the provider then try again.");
				Console.println("The operation has been aborted.");
			}
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}

}
