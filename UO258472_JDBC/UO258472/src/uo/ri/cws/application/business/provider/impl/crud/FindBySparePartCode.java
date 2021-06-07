package uo.ri.cws.application.business.provider.impl.crud;

import alb.util.assertion.Argument;
import alb.util.assertion.Assert;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartRecord;
import uo.ri.cws.application.persistence.supply.SupplyGateway;
import uo.ri.cws.application.persistence.supply.SupplyRecord;

import java.security.Provider;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindBySparePartCode implements Command<List<ProviderDto>> {

    private String sparePartCode;

    public FindBySparePartCode(String sparePartCode) {
        super();
        Argument.isTrue(sparePartCode.trim().length()!=0, "The sparePartCode cant be a blank");
        this.sparePartCode = sparePartCode;
    }

    public List<ProviderDto> execute() throws SQLException, BusinessException {
        List<ProviderDto> providers = new ArrayList<>();
        ProviderGateway pg = PersistenceFactory.forProviders();
        SupplyGateway sg = PersistenceFactory.forSupplies();
        SparePartGateway spg = PersistenceFactory.forSparePart();

        Optional<SparePartRecord> part = spg.findByCode(sparePartCode);
        BusinessCheck.isTrue(part.isPresent(), "This sparePart does not exist");

        List<SupplyRecord> supplies = sg.findProvidersBySparePartId(part.get().id);
        BusinessCheck.isTrue(!supplies.isEmpty(), "Error with the supply");

        for (SupplyRecord s: supplies) {
            ProviderDto p = DtoMapper.toDto(pg.findById(s.providerId).get());
            providers.add(p);
        }

        return providers;
    }

}
