package uo.ri.cws.application.business.mechanic.crud.commands;

import java.sql.SQLException;
import java.util.Optional;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicById implements Command<Optional<MechanicDto>>{

	private String idMechanic;

	public FindMechanicById(String idMechanic) {
		super();
		Argument.isTrue(idMechanic.trim().length()!=0, "The idMechanic cant be a blank");
		this.idMechanic = idMechanic;
	}

	public Optional<MechanicDto> execute() throws SQLException{
		Optional<MechanicDto> mechanic;
		MechanicGateway mg = PersistenceFactory.forMechanic();

		mechanic = DtoMapper.toDto(mg.findById(idMechanic));
		return mechanic;
	}
}
