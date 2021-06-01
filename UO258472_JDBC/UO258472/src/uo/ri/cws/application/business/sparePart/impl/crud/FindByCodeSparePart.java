package uo.ri.cws.application.business.sparePart.impl.crud;

import java.sql.SQLException;
import java.util.Optional;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.sparePart.SparePartReportDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.sparePart.SparePartReportGateway;

public class FindByCodeSparePart implements Command<Optional<SparePartReportDto>> {

	private String code;

	public FindByCodeSparePart(String code) {
		super();
		Argument.isNotNull(code,"The code cant be null");
		Argument.isTrue(code.trim().length()!=0, "The code cant be a blank");
		this.code = code;
	}
	
	
	@Override
	public Optional<SparePartReportDto> execute() throws SQLException {
		SparePartReportGateway spg = PersistenceFactory.forSparePartReport();

		if(spg.isPresent(code).isPresent()){
			System.out.println("isPresent");
			return DtoMapper.toDtoSPRep(spg.findByCode(code));
		} else {
			System.out.println("isNotPresent");
			return Optional.empty();
		}

	}
	
}
