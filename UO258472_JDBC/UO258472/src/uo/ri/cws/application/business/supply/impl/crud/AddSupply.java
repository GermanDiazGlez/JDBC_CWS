package uo.ri.cws.application.business.supply.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.provider.ProviderRecord;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartRecord;
import uo.ri.cws.application.persistence.supply.SupplyGateway;

import java.sql.SQLException;
import java.util.UUID;

public class AddSupply implements Command<String> {
    SupplyDto supply = new SupplyDto();

    public AddSupply(SupplyDto supply){
        super();
        Argument.isNotNull(supply.provider.nif,"The nif cant be null");
        Argument.isNotNull(supply.sparePart.code,"The sparePart code cant be null");
        Argument.isNotNull(supply.price,"The price cant be null");
        Argument.isNotNull(supply.deliveryTerm,"The deliveryTerm cant be null");
        Argument.isTrue(supply.provider.nif.trim().length()!=0, "The nif cant be a blank");
        Argument.isTrue(supply.sparePart.code.trim().length()!=0, "The sparePart code cant be a blank");
        this.supply = supply;
    }

    @Override
    public String execute() throws BusinessException, SQLException {
        SupplyGateway sg = PersistenceFactory.forSupplies();
        ProviderGateway pg = PersistenceFactory.forProviders();
        SparePartGateway spg = PersistenceFactory.forSparePart();

        BusinessCheck.isTrue(!pg.findProviderByNif(supply.provider.nif).isEmpty(), "There is not an provider with that nif");
        BusinessCheck.isTrue(!spg.findByCode(supply.sparePart.code).isEmpty(), "There is not an sparePart with that code");
        SparePartRecord sp = spg.findByCode(supply.sparePart.code).get();
        supply.sparePart.id = sp.id;
        supply.sparePart.description = sp.description;
        supply.sparePart.code = sp.code;

        ProviderRecord p = pg.findProviderByNif(supply.provider.nif).get();
        supply.provider.id = p.id;
        supply.provider.nif = p.nif;
        supply.provider.name = p.name;
        supply.id = UUID.randomUUID().toString();

        sg.add(DtoMapper.toRecord(supply));

        return supply.id;
    }
}
