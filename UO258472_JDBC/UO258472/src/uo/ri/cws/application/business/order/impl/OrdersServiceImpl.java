package uo.ri.cws.application.business.order.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrdersService;
import uo.ri.cws.application.business.order.impl.crud.FindByCode;
import uo.ri.cws.application.business.order.impl.crud.FindByProviderNif;
import uo.ri.cws.application.business.order.impl.crud.GenerateOrders;
import uo.ri.cws.application.business.order.impl.crud.ReceiveOrder;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class OrdersServiceImpl implements OrdersService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public List<OrderDto> generateOrders() throws BusinessException {
		GenerateOrders go = new GenerateOrders();
		return executor.execute(go);
	}

	@Override
	public List<OrderDto> findByProviderNif(String nif) throws BusinessException {
		FindByProviderNif fbi = new FindByProviderNif(nif);
		return executor.execute(fbi);
	}

	@Override
	public Optional<OrderDto> findByCode(String code) throws BusinessException {
		FindByCode fbc = new FindByCode(code);
		return executor.execute(fbc);
	}

	@Override
	public OrderDto receive(String code) throws BusinessException {
		ReceiveOrder ro = new ReceiveOrder(code);
		return executor.execute(ro);
	}

}
