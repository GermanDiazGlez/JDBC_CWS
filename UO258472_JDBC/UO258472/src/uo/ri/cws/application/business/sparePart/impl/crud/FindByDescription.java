package uo.ri.cws.application.business.sparePart.impl.crud;

import java.sql.SQLException;
import java.util.List;
import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.sparePart.SparePartReportDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.sparePart.SparePartReportGateway;

public class FindByDescription implements Command<List<SparePartReportDto>> {

	private String description;

	public FindByDescription(String description) {
		super();
		Argument.isNotNull(description,"The description cant be null");
		Argument.isTrue(description.trim().length()!=0, "The description cant be a blank");
		this.description = description;
	}
	
	
	@Override
	public List<SparePartReportDto> execute() throws BusinessException, SQLException {
		List<SparePartReportDto> spareParts;
		SparePartReportGateway spg = PersistenceFactory.forSparePartReport();

		spareParts = DtoMapper.toDtoListSPRep(spg.findByDescription(description));

		return spareParts;
	}

	
}
