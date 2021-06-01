package uo.ri.cws.application.ui.manager.spares.sparepart.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.sparePart.SparePartCrudService;
import uo.ri.cws.application.business.sparePart.SparePartDto;

public class AddAction implements Action {

	@Override
	public void execute() throws Exception {
		try{
			SparePartDto dto = new SparePartDto();

			Console.println("Please, provide the following data: ");
			dto.code = Console.readString("Code: ");
			dto.description = Console.readString("Description: ");
			dto.stock = Console.readInt("Current stock: ");
			dto.minStock = Console.readInt("Minimum stock: ");
			dto.maxStock = Console.readInt("Maximum stock: ");
			dto.price = Console.readDouble("Price: ");

			SparePartCrudService service = BusinessFactory.forSparePartCrudService();
			String id = service.add(dto);

			Console.println("The new spare part has been registered with id " +  id);
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}

}
