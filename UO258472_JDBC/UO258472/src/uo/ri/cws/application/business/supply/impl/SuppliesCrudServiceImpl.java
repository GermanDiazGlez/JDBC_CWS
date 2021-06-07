package uo.ri.cws.application.business.supply.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.supply.SuppliesCrudService;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.business.supply.impl.crud.AddSupply;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class SuppliesCrudServiceImpl implements SuppliesCrudService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public String add(SupplyDto dto) throws BusinessException {
		AddSupply as = new AddSupply(dto);
		return executor.execute(as);
	}

	@Override
	public void delete(String nif, String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SupplyDto> findByProviderNif(String nif) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SupplyDto> findBySparePartCode(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SupplyDto> findByNifAndCode(String nif, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SupplyDto dto) {
		// TODO Auto-generated method stub
		
	}

}
