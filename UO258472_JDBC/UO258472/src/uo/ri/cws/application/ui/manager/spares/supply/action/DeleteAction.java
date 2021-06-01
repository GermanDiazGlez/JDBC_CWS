package uo.ri.cws.application.ui.manager.spares.supply.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.supply.SuppliesCrudService;

public class DeleteAction implements Action {

	@Override
	public void execute() throws Exception {
		Console.println("Please, provide the following data: ");
		String nif = Console.readString("Provider nif: ");
		String code = Console.readString("Spare part code: ");

		SuppliesCrudService service = BusinessFactory.forSuppliesCrudService();
		service.delete(nif, code);

		Console.println("The supply has been deleted");
	}

}
