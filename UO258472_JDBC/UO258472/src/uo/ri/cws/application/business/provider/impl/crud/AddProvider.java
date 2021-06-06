package uo.ri.cws.application.business.provider.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.provider.ProviderGateway;

import java.sql.SQLException;
import java.util.UUID;

public class AddProvider implements Command<String> {

    ProviderDto provider = new ProviderDto();

    public AddProvider(ProviderDto provider){
        super();
        Argument.isNotNull(provider.nif,"The nif cant be null");
        Argument.isNotNull(provider.name,"The name cant be null");
        Argument.isNotNull(provider.email,"The email cant be null");
        Argument.isNotNull(provider.phone,"The phone cant be null");
        Argument.isTrue(provider.nif.trim().length()!=0, "The nif cant be a blank");
        Argument.isTrue(provider.name.trim().length()!=0, "The name cant be a blank");
        Argument.isTrue(provider.email.trim().length()!=0, "The email cant be a blank");
        Argument.isTrue(provider.phone.trim().length()!=0, "The phone cant be a blank");
        this.provider = provider;
    }

    public String execute() throws BusinessException, SQLException {
        ProviderGateway pg = PersistenceFactory.forProviders();

        BusinessCheck.isTrue(pg.findProviderByNif(provider.nif).isEmpty(), "There already exist another"
                + " provider with the same nif");

        BusinessCheck.isTrue(pg.findProviderByOthers(provider.name, provider.email, provider.phone).isEmpty(), "There already exist another"
                + " provider with the same name, email and phone");

        provider.id = UUID.randomUUID().toString();
        pg.add(DtoMapper.toRecord(provider));

        return provider.id;
    }
}
