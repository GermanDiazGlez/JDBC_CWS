package uo.ri.cws.application.ui.manager.spares.sparepart.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.sparePart.SparePartCrudService;

public class DeleteAction implements Action {

	@Override
	public void execute() throws Exception {
		try{
			String code = Console.readString("Code: ");

			SparePartCrudService service = BusinessFactory.forSparePartCrudService();
			service.delete(code);

			Console.println("The spare part has been deleted");
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}

}
