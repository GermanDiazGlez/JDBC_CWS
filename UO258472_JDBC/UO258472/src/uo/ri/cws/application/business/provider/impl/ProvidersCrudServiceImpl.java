package uo.ri.cws.application.business.provider.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.provider.ProvidersCrudService;
import uo.ri.cws.application.business.provider.impl.crud.*;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class ProvidersCrudServiceImpl implements ProvidersCrudService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public String add(ProviderDto provider) throws BusinessException {
		AddProvider ap = new AddProvider(provider);
		return executor.execute(ap);
	}

	@Override
	public void delete(String nif) throws BusinessException {
		DeleteProvider dp = new DeleteProvider(nif);
		executor.execute(dp);
	}

	@Override
	public List<ProviderDto> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProviderDto> findBySparePartCode(String code) throws BusinessException {
		FindBySparePartCode fc = new FindBySparePartCode(code);
		return executor.execute(fc);
	}

	@Override
	public Optional<ProviderDto> findByNif(String nif) throws BusinessException {
		FindProviderByNif fp = new FindProviderByNif(nif);
		return executor.execute(fp);
	}

	@Override
	public void update(ProviderDto provider) throws BusinessException {
		UpdateProvider up = new UpdateProvider(provider);
		executor.execute(up);
	}

}
