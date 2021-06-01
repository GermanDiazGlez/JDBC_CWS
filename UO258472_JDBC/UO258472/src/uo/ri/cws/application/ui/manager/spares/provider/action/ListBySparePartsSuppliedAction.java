package uo.ri.cws.application.ui.manager.spares.provider.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.provider.ProvidersCrudService;
import uo.ri.cws.application.ui.util.Printer;

public class ListBySparePartsSuppliedAction implements Action {

	@Override
	public void execute() throws Exception {
		String code = Console.readString("Spare part code: ");
		
		ProvidersCrudService service = BusinessFactory.forProvidersService();
		List<ProviderDto> providers = service.findBySparePartCode(code);
		
		Console.println("There are " + providers.size() + " providers");
		for(ProviderDto p: providers) {
			Printer.print(p);
		}
	}

}
