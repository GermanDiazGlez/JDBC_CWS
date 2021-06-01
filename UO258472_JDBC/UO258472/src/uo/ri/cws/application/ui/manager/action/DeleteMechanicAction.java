package uo.ri.cws.application.ui.manager.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicCrudService;

public class DeleteMechanicAction implements Action {


	@Override
	public void execute() throws BusinessException {
		String idMechanic = Console.readString("Type mechanic id "); 

		try{
			MechanicCrudService mcs = BusinessFactory.forMechanicCrudService();
			mcs.deleteMechanic(idMechanic);
			Console.println("Mechanic deleted");
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}

}
