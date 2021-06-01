package uo.ri.cws.application.ui.manager.spares.sparepart.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.sparePart.SparePartCrudService;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.ui.util.Printer;

public class UpdateAction implements Action {

	@Override
	public void execute() throws Exception {
		try{
			Console.println("Please, provide the following data: ");
			String code = Console.readString("Code: ");

			SparePartCrudService service = BusinessFactory.forSparePartCrudService();
			Optional<SparePartDto> op = service.findByCode( code );

			if ( op.isEmpty() ) {
				Console.println("There is no such spare part. ");
				Console.println("Please mind the code and try again.");
				return;
			}

			SparePartDto dto = op.get();
			Console.println("Current data for the spare part: ");
			Printer.print( dto );

			dto.description = Console.readString("new description: ");
			dto.stock = Console.readInt("new stock: ");
			dto.minStock = Console.readInt("new minimum stock: ");
			dto.maxStock = Console.readInt("new maximum stock: ");
			dto.price = Console.readDouble("new price: ");

			service.update(dto); // dto keeps the id and version

			Console.println("The spare part has been updated");
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}

}
