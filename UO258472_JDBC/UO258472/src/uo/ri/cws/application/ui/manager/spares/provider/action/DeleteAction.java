package uo.ri.cws.application.ui.manager.spares.provider.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.provider.ProvidersCrudService;

public class DeleteAction implements Action {

	@Override
	public void execute() throws Exception {
		Console.println("Please, provide the following data: ");
		String nif = Console.readString("Nif: ");
		
		ProvidersCrudService service = BusinessFactory.forProvidersService();
		service.delete(nif);

		Console.println("The provider has been deleted");
	}

}
