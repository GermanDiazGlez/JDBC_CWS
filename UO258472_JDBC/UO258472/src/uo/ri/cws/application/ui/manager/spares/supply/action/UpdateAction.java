package uo.ri.cws.application.ui.manager.spares.supply.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.supply.SuppliesCrudService;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.ui.util.Printer;

public class UpdateAction implements Action {

	@Override
	public void execute() throws Exception {
		Console.println("Please, provide the following data: ");
		String nif = Console.readString("Supply nif: ");
		String code = Console.readString("Spare part code: ");

		SuppliesCrudService service = BusinessFactory.forSuppliesCrudService();
		Optional<SupplyDto> op = service.findByNifAndCode( nif, code );
		
		if ( op.isEmpty() ) {
			Console.println("There is no such supply.");
			Console.println("Please mind the nif/code and try again");
			return;
		}
		
		SupplyDto dto = op.get();
		Console.println("Current data for the supply: ");
		Printer.print( dto );
		
		dto.price = Console.readDouble("new price: ");
		dto.deliveryTerm = Console.readInt("new delivery term: ");
		
		service.update(dto); // dto keeps the id and version
		
		Console.println("The supply has been updated");
	}

}
