package uo.ri.cws.application.business.sparePart.impl.crud;

import java.sql.SQLException;
import java.util.Optional;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;

public class FindByCode implements Command<Optional<SparePartDto>> {

	private String code;

	public FindByCode(String code) {
		super();
		Argument.isNotNull(code,"The code cant be null");
		Argument.isTrue(code.trim().length()!=0, "The code cant be a blank");
		this.code = code;
	}
	
	
	@Override
	public Optional<SparePartDto> execute() throws SQLException {
		SparePartGateway spg = PersistenceFactory.forSparePart();

		if(spg.isPresent(code).isPresent()){
			return DtoMapper.toDtoSP(spg.findByCode(code));
		} else {
			return Optional.empty();
		}

	}
	
}
