package uo.ri.cws.application.business.sparePart.impl.crud;

import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;

import java.sql.SQLException;
import java.util.List;

public class FindUnderStock implements Command<List<SparePartDto>>{

	@Override
	public List<SparePartDto> execute() throws SQLException {
		List<SparePartDto> spareParts;

		SparePartGateway spg = PersistenceFactory.forSparePart();

		spareParts = DtoMapper.toDtoListSP(spg.findUnderStock());

		return spareParts;
	}

}
