package uo.ri.cws.application.business.provider.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.supply.SupplyGateway;

import java.security.Provider;
import java.sql.SQLException;
import java.util.Optional;

public class FindBySparePartCode {

    private String sparePartCode;

    public FindBySparePartCode(String sparePartCode) {
        super();
        Argument.isTrue(sparePartCode.trim().length()!=0, "The sparePartCode cant be a blank");
        this.sparePartCode = sparePartCode;
    }

    public Optional<ProviderDto> execute() throws SQLException {
        Optional<ProviderDto> provider;
        ProviderGateway pg = PersistenceFactory.forProviders();
        SupplyGateway sg = PersistenceFactory.forSupplies();

        

        provider = DtoMapper.toDto(pg.findById(sparePartCode));
        return mechanic;
    }

}
