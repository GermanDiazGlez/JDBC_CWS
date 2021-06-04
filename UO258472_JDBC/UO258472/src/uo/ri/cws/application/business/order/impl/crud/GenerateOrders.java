package uo.ri.cws.application.business.order.impl.crud;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.business.order.OrderDto.OrderLineDto;
import uo.ri.cws.application.persistence.order.OrderGateway;
import uo.ri.cws.application.persistence.order.impl.OrderGatewayImpl;
import uo.ri.cws.application.persistence.orderLines.OrderLinesGateway;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.provider.ProviderRecord;
import uo.ri.cws.application.persistence.provider.impl.ProviderGatewayImpl;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartRecord;
import uo.ri.cws.application.persistence.sparePart.SparePartReportGateway;
import uo.ri.cws.application.persistence.sparePart.impl.SparePartGatewayImpl;
import uo.ri.cws.application.persistence.supply.SupplyGateway;
import uo.ri.cws.application.persistence.supply.SupplyRecord;
import uo.ri.cws.application.persistence.supply.impl.SupplyGatewayImpl;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class GenerateOrders implements Command<List<OrderDto>>{

	private SparePartGateway sparePartGateway = PersistenceFactory.forSparePart();
	private ProviderGateway providerGateway = PersistenceFactory.forProviders();
	private SupplyGateway supplyGateway = PersistenceFactory.forSupplies();
	private OrderGateway orderGateway = PersistenceFactory.forOrders();
	private OrderLinesGateway orderLinesGateway = PersistenceFactory.forOrderLines();


	@Override
	public List<OrderDto> execute() throws BusinessException, SQLException {
		List<SparePartRecord> parts = sparePartGateway.findUnderStock();

		List<OrderDto> orders = new ArrayList<>();

		for(SparePartRecord p : parts){
			generateOrder(p, orders);
		}
		return orders;
	}

	private OrderDto generateOrder(SparePartRecord p, List<OrderDto> orders) throws SQLException {
		OrderLineDto orderLineDto = new OrderLineDto();
		orderLineDto.sparePart = DtoMapper.toOrderedDto(p);
		orderLineDto.quantity = p.maxStock - p.stock;

		OrderDto orderDto = null;
		Optional<ProviderRecord> provider = findProvider(orderLineDto);

		if (provider.isPresent()){
			for(OrderDto o : orders){
				if(o.provider.id.equals(provider.get().id)){
					orderDto = o;
					break;
				}
			}

			if(orderDto == null){
				orderDto = createOrder();
				orderDto.provider = DtoMapper.toDtoForOrderedProvider(provider.get());
				orderGateway.add(DtoMapper.toRecord(orderDto));
				orders.add(orderDto);
			}

			orderLinesGateway.add(DtoMapper.toRecord(orderLineDto, orderDto.id));

			orderDto.lines.add(orderLineDto);
			orderDto.amount += orderLineDto.price * orderLineDto.quantity;
			orderGateway.update(DtoMapper.toRecord(orderDto));
		}

		return orderDto;
	}


	private Optional<ProviderRecord> findProvider(OrderLineDto orderLineDto) throws SQLException {
		Optional<ProviderRecord> provider = Optional.ofNullable(null);

		List<SupplyRecord> supplies = supplyGateway.findBestProviderBySparePartId(orderLineDto.sparePart.id);

		if(supplies.size() > 0){
			SupplyRecord supply = supplies.get(0);
			orderLineDto.price = supply.price;
			provider = providerGateway.findById(supply.providerId);
		}
		return provider;
	}

	private OrderDto createOrder() throws SQLException {
		OrderDto dto = new OrderDto();
		dto.code = UUID.randomUUID().toString();
		dto.id = UUID.randomUUID().toString();
		dto.orderedDate = LocalDate.now();
		dto.status = "PENDING";
		return dto;
	}
}
