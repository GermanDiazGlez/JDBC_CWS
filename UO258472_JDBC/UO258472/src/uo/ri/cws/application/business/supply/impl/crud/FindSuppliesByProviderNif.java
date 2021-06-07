package uo.ri.cws.application.business.supply.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.supply.SupplyGateway;

import java.security.Provider;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FindSuppliesByProviderNif implements Command<List<SupplyDto>> {
    private String nif;

    public FindSuppliesByProviderNif(String nif) {
        super();
        Argument.isNotNull(nif,"The nif cant be null");
        Argument.isTrue(nif.trim().length()!=0, "The nif cant be a blank");
        this.nif = nif;
    }

    @Override
    public List<SupplyDto> execute() throws BusinessException, SQLException {
        List<SupplyDto> supplies;
        SupplyDto supply = new SupplyDto();
        SupplyGateway sg = PersistenceFactory.forSupplies();
        ProviderGateway pg = PersistenceFactory.forProviders();
        SparePartGateway spg = PersistenceFactory.forSparePart();

        BusinessCheck.isTrue(pg.findProviderByNif(nif).isPresent(), "This provider does not exist");

        supply.provider.id = pg.findProviderByNif(nif).get().id;

        supplies = DtoMapper.toDto(sg.findSparePartIdsByIdProvider(supply.provider.id));

        for (SupplyDto s : supplies) {
            SparePartDto sparePart = DtoMapper.toDto(spg.findById(s.sparePart.id).get());
            s.sparePart.code = sparePart.code;
            s.sparePart.description = sparePart.description;

            s.provider.name = pg.findProviderByNif(nif).get().name;
            s.provider.nif = nif;
        }

        return supplies;
    }
}
