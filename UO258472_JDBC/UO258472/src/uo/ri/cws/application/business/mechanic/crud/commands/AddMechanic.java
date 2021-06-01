package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.SQLException;
import java.util.UUID;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class AddMechanic implements Command<MechanicDto>{

	MechanicDto mechanic = new MechanicDto();


	public AddMechanic(MechanicDto mechanic) {
		super();
		Argument.isNotNull(mechanic.dni,"The dni cant be null");
		Argument.isNotNull(mechanic.name,"The name cant be null");
		Argument.isNotNull(mechanic.surname,"The surname cant be null");
		Argument.isTrue(mechanic.dni.trim().length()!=0, "The dni cant be a blank");
		Argument.isTrue(mechanic.name.trim().length()!=0, "The name cant be a blank");
		Argument.isTrue(mechanic.surname.trim().length()!=0, "The surname cant be a blank");
		this.mechanic = mechanic;
	}

	public MechanicDto execute() throws BusinessException, SQLException{
		MechanicGateway mg = PersistenceFactory.forMechanic();

		BusinessCheck.isTrue(mg.findByDni(mechanic.dni).isEmpty(), "There already exist another"
				+ " mechanic with the same dni");
		mechanic.id = UUID.randomUUID().toString();
		mg.add(DtoMapper.toRecord(mechanic));

		return mechanic;
	}

}
