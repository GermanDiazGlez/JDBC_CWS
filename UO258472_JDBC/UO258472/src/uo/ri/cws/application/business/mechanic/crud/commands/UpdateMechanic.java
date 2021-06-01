package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.SQLException;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class UpdateMechanic implements Command<Void>{

	MechanicDto mechanic;

	public UpdateMechanic(MechanicDto mechanic) {
		super();
		Argument.isNotNull(mechanic.id,"The idMechanic cant be null");
		Argument.isNotNull(mechanic.name,"The name cant be null");
		Argument.isNotNull(mechanic.surname,"The surname cant be null");
		Argument.isTrue(mechanic.id.trim().length()!=0, "The idMechanic cant be a blank");
		Argument.isTrue(mechanic.name.trim().length()!=0, "The name cant be a blank");
		Argument.isTrue(mechanic.surname.trim().length()!=0, "The surname cant be a blank");
		this.mechanic = mechanic;
	}

	public Void execute() throws BusinessException, SQLException{
		MechanicGateway mg = PersistenceFactory.forMechanic();

		BusinessCheck.isTrue(mg.findById(mechanic.id).isPresent(), "The mechanic id does not exist");
		BusinessCheck.isTrue(mechanic.name!=null, "The mechanic name must be not null");
		BusinessCheck.isTrue(mechanic.surname!=null, "The mechanic surname must be not null");
		mg.update(DtoMapper.toRecord(mechanic));

		return null;
	}

}
