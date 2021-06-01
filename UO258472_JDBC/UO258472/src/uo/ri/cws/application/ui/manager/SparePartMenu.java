package uo.ri.cws.application.ui.manager;

import alb.util.menu.BaseMenu;
import uo.ri.cws.application.ui.manager.spares.sparepart.action.AddAction;
import uo.ri.cws.application.ui.manager.spares.sparepart.action.DeleteAction;
import uo.ri.cws.application.ui.manager.spares.sparepart.action.ListUnderStockAction;
import uo.ri.cws.application.ui.manager.spares.sparepart.action.UpdateAction;

public class SparePartMenu extends BaseMenu {

	public SparePartMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Spare parts management", null},
			
			{ "Add spare part", 				AddAction.class }, 
			{ "Update spare part", 	UpdateAction.class }, 
			{ "Delete spare part", 				DeleteAction.class }, 
			{ "List spare parts", 				ListUnderStockAction.class },
		};
	}

}
