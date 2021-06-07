package uo.ri.cws.application.business.supply.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.supply.SupplyGateway;

import java.sql.SQLException;

public class UpdateSupply implements Command<Void> {
    private SupplyDto supply;

    public UpdateSupply(SupplyDto supply) {
        super();
        Argument.isNotNull(supply.price,"The price cant be null");
        Argument.isNotNull(supply.deliveryTerm,"The deliveryTerm cant be null");
        this.supply = supply;
    }

    @Override
    public Void execute() throws BusinessException, SQLException {
        SupplyGateway sg = PersistenceFactory.forSupplies();

        BusinessCheck.isTrue(sg.findById(supply.id).isPresent(), "The supply id does not exist");
        sg.update(DtoMapper.toRecord(supply));

        return null;
    }
}
