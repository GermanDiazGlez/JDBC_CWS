package uo.ri.cws.application.ui.manager.spares;

import alb.util.menu.BaseMenu;
import uo.ri.cws.application.ui.manager.spares.order.OrdersMenu;
import uo.ri.cws.application.ui.manager.spares.provider.ProvidersMenu;
import uo.ri.cws.application.ui.manager.spares.sparepart.SparePartsMenu;
import uo.ri.cws.application.ui.manager.spares.supply.SuppliesMenu;

public class SparePartsManagementMenu extends BaseMenu {

	public SparePartsManagementMenu() {
		menuOptions = new Object[][] {
			{"Manager > Parts management", null},

			{ "Spare parts management", SparePartsMenu.class },
			{ "Providers management", 	ProvidersMenu.class },
			{ "Supplies management", 	SuppliesMenu.class },
			{ "Orders management", 		OrdersMenu.class },
		};
	}

}
