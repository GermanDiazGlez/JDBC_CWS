package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.SQLException;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class DeleteMechanic implements Command<Void>{


	private String idMechanic;

	public DeleteMechanic(String idMechanic) {
		super();
		Argument.isNotNull(idMechanic,"The idMechanic cant be null");
		Argument.isTrue(idMechanic.trim().length()!=0, "The idMechanic cant be a blank");
		this.idMechanic = idMechanic;
	}

	public Void execute() throws BusinessException, SQLException{
		MechanicGateway mg = PersistenceFactory.forMechanic();

		BusinessCheck.isTrue(mg.findById(idMechanic).isPresent(), "This mechanic does not exist");
		mg.remove(idMechanic);

		return null;

	}
}
