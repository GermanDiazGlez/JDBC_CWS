package uo.ri.cws.application.business.order.impl.crud;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.order.OrderGateway;
import uo.ri.cws.application.persistence.order.OrderRecord;
import uo.ri.cws.application.persistence.provider.ProviderGateway;

public class FindByProviderNif implements Command<List<OrderDto>>{
	
	private String nif;

	public FindByProviderNif(String nif) {
		super();
		Argument.isTrue(nif.trim().length()!=0, "The nif cant be a blank");
		this.nif = nif;
	}

	@Override
	public List<OrderDto> execute() throws SQLException {
		List<OrderDto> orders = new ArrayList<>();
		OrderGateway og = PersistenceFactory.forOrders();
		ProviderGateway pg = PersistenceFactory.forProviders();

		if(pg.findProviderByNif(nif).isPresent()) {

			ProviderDto provider = DtoMapper.toDto(pg.findProviderByNif(nif).get());

			orders = DtoMapper.toDtoListOrd(og.findOrdersByProviderNif(provider.id));

			for (OrderDto orderDto : orders) {
				orderDto.provider.id = provider.id;
				orderDto.provider.name = provider.name;
				orderDto.provider.nif = provider.nif;
			}
		}
		return orders;
	}

}
