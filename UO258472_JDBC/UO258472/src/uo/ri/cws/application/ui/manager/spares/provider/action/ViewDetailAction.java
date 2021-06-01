package uo.ri.cws.application.ui.manager.spares.provider.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.provider.ProvidersCrudService;
import uo.ri.cws.application.ui.util.Printer;

public class ViewDetailAction implements Action {

	@Override
	public void execute() throws Exception {
		Console.println("Please, provide the following data: ");
		String nif = Console.readString("Nif: ");
		
		ProvidersCrudService service = BusinessFactory.forProvidersService();
		Optional<ProviderDto> op = service.findByNif( nif );
		
		if ( op.isEmpty() ) {
			Console.println("There is no such provider.");
			Console.println("Please mind the nif and try again.");
			return;
		}
		
		Printer.print( op.get() );
	}

}
