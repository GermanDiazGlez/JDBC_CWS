package uo.ri.cws.application.ui.manager.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicCrudService;
import uo.ri.cws.application.business.mechanic.MechanicDto;

public class AddMechanicAction implements Action {


	@Override
	public void execute() throws BusinessException {

		try{
			// Get info
			MechanicDto mechanic = new MechanicDto();
			mechanic.dni = Console.readString("Dni");
			mechanic.name = Console.readString("Name");
			mechanic.surname = Console.readString("Surname");

			MechanicCrudService mcs = BusinessFactory.forMechanicCrudService();
			mcs.addMechanic(mechanic);

			// Print result
			Console.println("Mechanic added");
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}

}
