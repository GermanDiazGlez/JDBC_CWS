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

public class DeleteSupply implements Command<Void> {
    private String nif;
    private String code;


    public DeleteSupply(String nif, String code) {
        super();
        Argument.isNotNull(nif,"The nif cant be null");
        Argument.isNotNull(code,"The code cant be null");
        Argument.isTrue(nif.trim().length()!=0, "The nif cant be a blank");
        Argument.isTrue(code.trim().length()!=0, "The code cant be a blank");
        this.nif = nif;
        this.code = code;
    }

    @Override
    public Void execute() throws BusinessException, SQLException {
        SupplyGateway sg = PersistenceFactory.forSupplies();
        ProviderGateway pg = PersistenceFactory.forProviders();
        SparePartGateway spg = PersistenceFactory.forSparePart();

        BusinessCheck.isTrue(pg.findProviderByNif(nif).isPresent(), "This provider does not exist");
        BusinessCheck.isTrue(spg.findByCode(code).isPresent(), "This sparePart does not exist");

        ProviderDto provider = DtoMapper.toDto(pg.findProviderByNif(nif).get());
        SparePartDto sparePart = DtoMapper.toDto(spg.findByCode(code).get());

        BusinessCheck.isTrue(sg.findSupplyByIdProviderAndIdSparePart(provider.id, sparePart.id).isPresent(), "This supply does not exist");
        SupplyDto supply = DtoMapper.toDtoSupplyComplete(sg.findSupplyByIdProviderAndIdSparePart(provider.id, sparePart.id).get());

        sg.remove(supply.id);

        return null;
    }
}
