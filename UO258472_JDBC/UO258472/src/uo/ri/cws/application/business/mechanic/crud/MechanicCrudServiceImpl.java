package uo.ri.cws.application.business.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicCrudService;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.mechanic.crud.commands.*;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class MechanicCrudServiceImpl implements MechanicCrudService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public MechanicDto addMechanic(MechanicDto mechanic) throws BusinessException {
		AddMechanic am = new AddMechanic(mechanic);
		return executor.execute(am);
	}

	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		DeleteMechanic dm = new DeleteMechanic(idMechanic);
		executor.execute(dm);
	}

	@Override
	public void updateMechanic(MechanicDto mechanic) throws BusinessException {
		UpdateMechanic um = new UpdateMechanic(mechanic);
		executor.execute(um);

	}

	@Override
	public Optional<MechanicDto> findMechanicById(String idMechanic) throws BusinessException {
		FindMechanicById fm = new FindMechanicById(idMechanic);
		return executor.execute(fm);
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		FindAllMechanics fam = new FindAllMechanics();
		return executor.execute(fam);
	}

}
