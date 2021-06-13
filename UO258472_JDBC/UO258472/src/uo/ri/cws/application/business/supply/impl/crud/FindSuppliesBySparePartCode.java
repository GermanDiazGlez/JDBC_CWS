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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindSuppliesBySparePartCode implements Command<List<SupplyDto>> {
    private String sparePartCode;

    public FindSuppliesBySparePartCode(String sparePartCode) {
        super();
        Argument.isNotNull(sparePartCode,"The sparePart code cant be null");
        Argument.isTrue(sparePartCode.trim().length()!=0, "The sparePart code cant be a blank");
        this.sparePartCode = sparePartCode;
    }

    @Override
    public List<SupplyDto> execute() throws SQLException {
        List<SupplyDto> supplies = new ArrayList<>();
        SupplyDto supply = new SupplyDto();
        SupplyGateway sg = PersistenceFactory.forSupplies();
        SparePartGateway spg = PersistenceFactory.forSparePart();
        ProviderGateway pg = PersistenceFactory.forProviders();

        if(spg.findByCode(sparePartCode).isPresent()) {

            supply.sparePart.id = spg.findByCode(sparePartCode).get().id;

            supplies = DtoMapper.toDto(sg.findProvidersBySparePartId(supply.sparePart.id));

            for (SupplyDto s : supplies) {
                s.provider.nif = pg.findById(s.provider.id).get().nif;
                s.provider.name = pg.findById(s.provider.id).get().name;
                s.sparePart.code = sparePartCode;
                s.sparePart.description = spg.findByCode(sparePartCode).get().description;
            }
        }
        return supplies;
    }
}