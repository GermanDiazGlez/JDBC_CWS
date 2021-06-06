package uo.ri.cws.application.business.provider.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.provider.ProviderGateway;

import java.sql.SQLException;
import java.util.Optional;

public class FindProviderByNif implements Command<Optional<ProviderDto>> {

    private String nif;

    public FindProviderByNif(String nif) {
        super();
        Argument.isTrue(nif.trim().length()!=0, "The nif cant be a blank");
        this.nif = nif;
    }

    public Optional<ProviderDto> execute() throws SQLException {
        Optional<ProviderDto> provider;
        ProviderGateway pg = PersistenceFactory.forProviders();

        provider = DtoMapper.toDtoPro(pg.findProviderByNif(nif));

        return provider;
    }
}
