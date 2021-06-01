package uo.ri.cws.application.ui.manager;

import alb.util.menu.BaseMenu;
import uo.ri.cws.application.ui.manager.spares.order.OrdersMenu;
import uo.ri.cws.application.ui.manager.spares.sparepart.SparePartsMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrator", OrdersMenu.class },
			{ "Mechanics management", 			MechanicMenu.class }, 
			{ "Spare parts management", 			SparePartsMenu.class },
			{ "Vehicle types management", 	VehicleTypeMenu.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
		System.out.println();
	}

}
