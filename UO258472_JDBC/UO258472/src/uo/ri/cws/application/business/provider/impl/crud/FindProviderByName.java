package uo.ri.cws.application.business.provider.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.provider.ProviderGateway;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FindProviderByName implements Command<List<ProviderDto>> {

    private String name;

    public FindProviderByName(String name) {
        super();
        Argument.isTrue(name.trim().length()!=0, "The name cant be a blank");
        this.name = name;
    }

    public List<ProviderDto> execute() throws SQLException {
        List<ProviderDto> providers;
        ProviderGateway pg = PersistenceFactory.forProviders();

        providers = DtoMapper.toDtoListProv(pg.findProviderByName(name));

        return providers;
    }
}
