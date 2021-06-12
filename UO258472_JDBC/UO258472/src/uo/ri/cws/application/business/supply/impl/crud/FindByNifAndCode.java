package uo.ri.cws.application.business.supply.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.supply.SupplyGateway;

import java.sql.SQLException;
import java.util.Optional;

public class FindByNifAndCode implements Command<Optional<SupplyDto>> {
    private String nif;
    private String code;

    public FindByNifAndCode(String nif, String code) {
        super();
        Argument.isNotNull(nif,"The nif cant be null");
        Argument.isNotNull(code,"The code cant be null");
        Argument.isTrue(nif.trim().length()!=0, "The nif cant be a blank");
        Argument.isTrue(code.trim().length()!=0, "The code cant be a blank");
        this.nif = nif;
        this.code = code;
    }

    @Override
    public Optional<SupplyDto> execute() throws BusinessException, SQLException {

        SupplyGateway sg = PersistenceFactory.forSupplies();
        ProviderGateway pg = PersistenceFactory.forProviders();
        SparePartGateway spg = PersistenceFactory.forSparePart();

        BusinessCheck.isTrue(pg.findProviderByNif(nif).isPresent(), "This provider does not exist");
        BusinessCheck.isTrue(spg.findByCode(code).isPresent(), "This sparePart does not exist");

        ProviderDto provider = DtoMapper.toDto(pg.findProviderByNif(nif).get());
        SparePartDto sparePart = DtoMapper.toDto(spg.findByCode(code).get());

        BusinessCheck.isTrue(sg.findSupplyByIdProviderAndIdSparePart(provider.id, sparePart.id).isPresent(), "This supply does not exist");
        Optional<SupplyDto> supply = DtoMapper.toDtoCompleteSupply(sg.findSupplyByIdProviderAndIdSparePart(provider.id, sparePart.id));

        supply.get().sparePart.code = sparePart.code;
        supply.get().sparePart.description = sparePart.description;
        supply.get().provider.nif = provider.nif;
        supply.get().provider.name = provider.name;

        return supply;
    }
}
