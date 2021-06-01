package uo.ri.cws.application.ui.manager.spares.provider.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.provider.ProvidersCrudService;
import uo.ri.cws.application.ui.util.Printer;

public class ListByNameAction implements Action {

	@Override
	public void execute() throws Exception {
		String name = Console.readString("Please, type the name or part: ");
		
		ProvidersCrudService service = BusinessFactory.forProvidersService();
		List<ProviderDto> providers = service.findByName( name );
		
		Console.println("There are " + providers.size() + " providers");
		for(ProviderDto p: providers) {
			Printer.print(p);
		}
	}

}
