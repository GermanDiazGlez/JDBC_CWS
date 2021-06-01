package uo.ri.cws.application.ui.manager.spares.supply.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.supply.SuppliesCrudService;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.ui.util.Printer;

public class ListBySparePartAction implements Action {

	@Override
	public void execute() throws Exception {
		String code = Console.readString("Spare part code: ");
		
		SuppliesCrudService service = BusinessFactory.forSuppliesCrudService();
		List<SupplyDto> supplies = service.findBySparePartCode( code );
		
		Console.println("There are " + supplies.size() + " supplies for the spare part");
		for(SupplyDto p: supplies) {
			Printer.print(p);
		}
	}

}
