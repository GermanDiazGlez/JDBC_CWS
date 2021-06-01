package uo.ri.cws.application.business.sparePart.impl.crud;

import java.sql.SQLException;
import java.util.UUID;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;

public class AddSparePart implements Command<String>{
	

	SparePartDto sparePart = new SparePartDto();


	public AddSparePart(SparePartDto sparePart) {
		super();
		Argument.isNotNull(sparePart.code,"The code cant be null");
		Argument.isNotNull(sparePart.description,"The description cant be null");
		Argument.isNotNull(sparePart.stock,"The stock cant be null");
		Argument.isNotNull(sparePart.maxStock,"The maxStock cant be null");
		Argument.isNotNull(sparePart.minStock,"The minStock cant be null");
		Argument.isTrue(sparePart.code.trim().length()!=0, "The code cant be a blank");
		Argument.isTrue(sparePart.description.trim().length()!=0, "The description cant be a blank");
		this.sparePart = sparePart;
	}

	public String execute() throws BusinessException, SQLException{

		SparePartGateway spg = PersistenceFactory.forSparePart();


		//Si ocurre algun problema, hacemos rollback. Aunque no tenga sentido en un Select, lo vamos a hacer de ejemplo.
		BusinessCheck.isTrue(spg.findByCode(sparePart.code).isEmpty(), "There already exist another"
				+ " spare part with the same code");
		BusinessCheck.isTrue(sparePart.code!=null, "The spare part code cant be null or empty");
		BusinessCheck.isTrue(sparePart.price>=0, "The spare part price cant be negative");
		BusinessCheck.isTrue(sparePart.stock>=0, "The spare part price stock cant be negative");
		BusinessCheck.isTrue(sparePart.maxStock>=0, "The spare part price maxStock cant be negative");
		BusinessCheck.isTrue(sparePart.minStock>=0, "The spare part price minStock cant be negative");

		sparePart.id = UUID.randomUUID().toString();
		spg.add(DtoMapper.toRecord(sparePart));

		return sparePart.id;
	}

}
