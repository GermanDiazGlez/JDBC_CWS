package uo.ri.cws.application.business.sparePart.impl.crud;

import java.sql.SQLException;
import java.util.Optional;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;

public class FindByIdSparePart implements Command<Optional<SparePartDto>>{
	
	private String id;

	public FindByIdSparePart(String id) {
		super();
		Argument.isNotNull(id,"The sparepartId cant be null");
		Argument.isTrue(id.trim().length()!=0, "The sparepartId cant be a blank");
		this.id = id;
	}

	@Override
	public Optional<SparePartDto> execute() throws SQLException {
		SparePartGateway spg = PersistenceFactory.forSparePart();

		if(spg.findByCode(id).isPresent()){
			return DtoMapper.toDtoSP(spg.findByCode(id));
		} else {
			return Optional.empty();
		}

	}

}
