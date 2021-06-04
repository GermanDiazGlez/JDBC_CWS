package uo.ri.cws.application.business.sparePart.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.sparePart.SparePartCrudService;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.sparePart.impl.crud.*;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class SparePartCrudServiceImpl implements SparePartCrudService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public String add(SparePartDto sp) throws BusinessException {
		AddSparePart asp = new AddSparePart(sp);
		return executor.execute(asp);
	}

	@Override
	public void delete(String code) throws BusinessException {
		DeleteSparePart dps = new DeleteSparePart(code);
		executor.execute(dps);
		
	}

	@Override
	public Optional<SparePartDto> findByCode(String code) throws BusinessException {
		FindByCode fbc = new FindByCode(code);
		return executor.execute(fbc);
	}
	
	@Override
	public Optional<SparePartDto> findById(String id) throws BusinessException {
		FindByIdSparePart fbi = new FindByIdSparePart(id);
		return executor.execute(fbi);
	}

	@Override
	public void update(SparePartDto sp) throws BusinessException {
		UpdateSparePart usp = new UpdateSparePart(sp);
		executor.execute(usp);
	}

	@Override
	public List<SparePartDto> findAll() throws BusinessException {
		FindAllSpareParts fam = new FindAllSpareParts();
		return executor.execute(fam);
	}

	@Override
	public List<SparePartDto> findUnderStock() throws BusinessException {
		FindUnderStock fus = new FindUnderStock();
		return executor.execute(fus);
	}


}
