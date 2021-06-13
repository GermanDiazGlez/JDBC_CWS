package uo.ri.cws.application.business.order.impl.crud;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrderDto.OrderLineDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.order.OrderGateway;
import uo.ri.cws.application.persistence.orderLines.OrderLinesGateway;
import uo.ri.cws.application.persistence.provider.ProviderGateway;

public class FindByCode implements Command<Optional<OrderDto>>{
	
	private String code;

	public FindByCode(String code) {
		super();
		Argument.isTrue(code.trim().length()!=0, "The code cant be a blank");
		this.code = code;
	}

	@Override
	public Optional<OrderDto> execute() throws SQLException {
		OrderGateway og = PersistenceFactory.forOrders();
		OrderLinesGateway olg = PersistenceFactory.forOrderLines();
		ProviderGateway pg = PersistenceFactory.forProviders();

		if(og.findByCode(code).isPresent()) {
			OrderDto order = DtoMapper.toDtoOr(og.findByCode(code)).get();

			ProviderDto provider = DtoMapper.toDto(pg.findProviderNameByOrderCode(code).get());

			order.provider.id = provider.id;
			order.provider.name = provider.name;
			order.provider.nif = provider.nif;

			List<OrderLineDto> lines = DtoMapper.toDtoListOrdLin(olg.getLinesForOrder(order.id));

			order.lines = lines;

			Optional<OrderDto> optional = Optional.of(order);
			return optional;
		}
		return Optional.empty();
	}

}
