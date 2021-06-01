package uo.ri.cws.application.business.sparePart.impl.crud;

import java.sql.SQLException;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.order.OrderGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.substitution.SubstitutionGateway;
import uo.ri.cws.application.persistence.supply.SupplyGateway;

public class DeleteSparePart implements Command<Void>{

	private String code;

	public DeleteSparePart(String code) {
		super();
		Argument.isNotNull(code,"The code cant be null");
		Argument.isTrue(code.trim().length()!=0, "The code cant be a blank");
		this.code = code;
	}

	public Void execute() throws BusinessException, SQLException{
		SparePartGateway spg = PersistenceFactory.forSparePart();
		SubstitutionGateway sbg = PersistenceFactory.forSubstitutions();
		SupplyGateway syg = PersistenceFactory.forSupplies();
		OrderGateway og = PersistenceFactory.forOrders();


		BusinessCheck.isTrue(spg.findByCode(code).isPresent(), "This spare part does not exist");
		SparePartDto sparePart = DtoMapper.toDto(spg.findByCode(code).get());
		BusinessCheck.isTrue(!sbg.findBySparePartId(sparePart.id).isPresent(), "Esta spare part esta siendo utilizada en substitution");
		BusinessCheck.isTrue(!og.findBySparePartCode(code).isPresent(), "Esta spare part esta siendo utilizada en una order");
		BusinessCheck.isTrue(!syg.findBySparePartId(sparePart.id).isPresent(), "Esta spare part esta siendo utilizada en supplies");
		
		spg.remove(code);

		return null;
	}

}
