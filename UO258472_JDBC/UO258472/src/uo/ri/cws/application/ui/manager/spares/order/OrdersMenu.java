package uo.ri.cws.application.ui.manager.spares.order;

import alb.util.menu.BaseMenu;
import uo.ri.cws.application.ui.manager.spares.order.action.GenerateOrdersAction;
import uo.ri.cws.application.ui.manager.spares.order.action.ListByProviderAction;
import uo.ri.cws.application.ui.manager.spares.order.action.ViewOrderDetailAction;

public class OrdersMenu extends BaseMenu {

	public OrdersMenu() {
		menuOptions = new Object[][] {
			{"Manager > Parts management > Orders", null},

			{ "Generate", 			GenerateOrdersAction.class },
			{ "View order detail", 	ViewOrderDetailAction.class },
			{ "List by provider", 	ListByProviderAction.class },
			{ "Receive order", 		null },
		};
	}

}
