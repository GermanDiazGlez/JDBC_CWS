package uo.ri.cws.application.business.provider.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.provider.ProviderRecord;
import uo.ri.cws.application.persistence.supply.SupplyGateway;

import java.sql.SQLException;
import java.util.Optional;

public class DeleteProvider implements Command<Void> {

    private String nif;

    public DeleteProvider(String nif) {
        super();
        Argument.isNotNull(nif,"The nif cant be null");
        Argument.isTrue(nif.trim().length()!=0, "The nif cant be a blank");
        this.nif = nif;
    }

    public Void execute() throws BusinessException, SQLException {
        ProviderGateway pg = PersistenceFactory.forProviders();
        SupplyGateway sp = PersistenceFactory.forSupplies();


        BusinessCheck.isTrue(pg.findProviderByNif(nif).isPresent(), "This provider does not exist");

        ProviderRecord prov = pg.findProviderByNif(nif).get();

        BusinessCheck.isFalse(sp.findProviderById(prov.id).isPresent(), "This provider has supplies with some SparePart");

        pg.remove(nif);

        return null;
    }
}
