package uo.ri.cws.application.business.provider.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.provider.ProviderGateway;

import java.sql.SQLException;

public class UpdateProvider implements Command<Void>{

	ProviderDto provider;

	public UpdateProvider(ProviderDto provider) {
		super();
		Argument.isNotNull(provider.email,"The nif cant be null");
		Argument.isNotNull(provider.name,"The name cant be null");
		Argument.isNotNull(provider.phone,"The phone cant be null");
		Argument.isTrue(provider.email.trim().length()!=0, "The email cant be a blank");
		Argument.isTrue(provider.name.trim().length()!=0, "The name cant be a blank");
		Argument.isTrue(provider.phone.trim().length()!=0, "The surname cant be a blank");
		this.provider = provider;
	}

	public Void execute() throws BusinessException, SQLException{
		ProviderGateway pg = PersistenceFactory.forProviders();

		pg.update(DtoMapper.toRecord(provider));

		return null;
	}

}
