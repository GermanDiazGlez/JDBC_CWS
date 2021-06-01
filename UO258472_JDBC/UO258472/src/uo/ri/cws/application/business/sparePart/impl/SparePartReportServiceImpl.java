package uo.ri.cws.application.business.sparePart.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.sparePart.SparePartReportDto;
import uo.ri.cws.application.business.sparePart.SparePartReportService;
import uo.ri.cws.application.business.sparePart.impl.crud.FindByCodeSparePart;
import uo.ri.cws.application.business.sparePart.impl.crud.FindByDescription;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class SparePartReportServiceImpl implements SparePartReportService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public List<SparePartReportDto> findByDescription(String desc) throws BusinessException {
		FindByDescription fbd = new FindByDescription(desc);
		return executor.execute(fbd);
	}

	@Override
	public List<SparePartReportDto> findUnderStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SparePartReportDto> findByCode(String code) throws BusinessException {
		FindByCodeSparePart fbc = new FindByCodeSparePart(code);
		return executor.execute(fbc);
	}

}
