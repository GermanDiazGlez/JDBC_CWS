package uo.ri.cws.application.ui.manager.spares.order.action;

import java.util.Comparator;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrdersService;
import uo.ri.cws.application.ui.util.Printer;

public class ListByProviderAction implements Action {

	@Override
	public void execute() throws Exception {
		String nif = Console.readString("Please, type the provider nif: ");

		try{

			OrdersService service = BusinessFactory.forOrdersService();
			List<OrderDto> orders = service.findByProviderNif(nif);
			for (OrderDto o : orders) {
				System.out.println(o.orderedDate);
			}

			orders.sort( new OrdersComparator() );

			System.out.println("Despuesde ordenar");
			for (OrderDto o : orders) {
				System.out.println(o.orderedDate);
			}

			for(OrderDto o: orders) {
				Printer.printSummary(o);
			}
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}

	public class OrdersComparator implements Comparator<OrderDto> {
		@Override
		public int compare(OrderDto o1, OrderDto o2) {
			int diff = o1.status.compareTo( o2.status );
			if ( diff == 0) {
				diff = o1.orderedDate.compareTo( o2.orderedDate );
			}
			return diff;
		}
	}

}
