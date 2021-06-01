package uo.ri.cws.application.ui.manager.spares.provider.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.provider.ProvidersCrudService;
import uo.ri.cws.application.ui.util.Printer;

public class UpdateAction implements Action {

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
		
		ProviderDto dto = op.get();
		Console.println("Current data for the provider: ");
		Printer.print( dto );
		
		dto.name = Console.readString("new name: ");
		dto.email = Console.readString("new email: ");
		dto.phone = Console.readString("new phone: ");
		
		service.update(dto); // dto keeps the id and version
		
		Console.println("The provider has been updated");
	}

}
