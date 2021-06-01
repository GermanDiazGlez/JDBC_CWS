package uo.ri.cws.application.ui.manager.spares.sparepart.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.sparePart.SparePartCrudService;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.ui.util.Printer;

public class ListUnderStockAction implements Action {

	@Override
	public void execute() throws Exception {
		SparePartCrudService service = BusinessFactory.forSparePartCrudService();
		List<SparePartDto> spares = service.findAll();
		
		Console.println("There are " + spares.size() + " spares");
		for(SparePartDto sp: spares) {
			Printer.print(sp);
		}
	}

}
