package uo.ri.cws.application.business.order.impl.crud;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.order.OrderGateway;
import uo.ri.cws.application.persistence.order.impl.OrderGatewayImpl;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.provider.impl.ProviderGatewayImpl;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.sparePart.impl.SparePartGatewayImpl;
import uo.ri.cws.application.persistence.supply.SupplyGateway;
import uo.ri.cws.application.persistence.supply.impl.SupplyGatewayImpl;

public class GenerateOrders implements Command<List<OrderDto>>{

	@Override
	public List<OrderDto> execute() throws BusinessException, SQLException {
		List<SparePartDto> spareParts;
		List<OrderDto> ordersToGenerate = new ArrayList<>();
		OrderGateway og = new OrderGatewayImpl();
		ProviderGateway pg = new ProviderGatewayImpl();
		SparePartGateway spg = new SparePartGatewayImpl();
		SupplyGateway sg = new SupplyGatewayImpl();
		boolean exist = false;

		//Primero sacamos las piezas cullo stock es menor que el minStock
		spareParts = DtoMapper.toDtoListSP(spg.findAll());

		for (SparePartDto sparePart: spareParts) {
			//Obtenemos el mejor proveedor para cada sparePart
			SupplyDto supply = DtoMapper.toDto(sg.findBestProviderBySparePartId(sparePart.id).get());
			OrderDto.OrderLineDto orderLine = new OrderDto.OrderLineDto();
			OrderDto.OrderedSpareDto spare = new OrderDto.OrderedSpareDto();
			spare.id = sparePart.id;
			spare.description = sparePart.description;
			orderLine.sparePart = spare;
			orderLine.price = supply.price;
			int amountToOrder = sparePart.maxStock-sparePart.stock;
			orderLine.quantity = amountToOrder;
			double amount = amountToOrder * orderLine.price;

			//Si el pedido ya existe, añadimos las lines y sino lo creamos
			exist = false;
			for (OrderDto order: ordersToGenerate) {
				if(supply.provider.id != null){
					if(order.provider.id==supply.provider.id){
						order.lines.add(orderLine);
						order.amount += amount;
						order.provider.id = supply.provider.id;
						ProviderDto prov = DtoMapper.toDto(pg.findById(supply.provider.id).get());
						order.provider.nif = prov.nif;
						order.provider.name = prov.name;
						LocalDate date = LocalDate.now();
						order.orderedDate = date;
						order.status = "PENDING";
						exist = true;
					}
				}
			}
			if(supply.provider.id != null) {
				if (!exist) {
					OrderDto order = new OrderDto();
					long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
					order.code = String.valueOf(number);
					order.provider.id = supply.provider.id;
					order.lines.add(orderLine);
					order.amount = amount;
					ProviderDto prov = DtoMapper.toDto(pg.findById(supply.provider.id).get());
					order.provider.nif = prov.nif;
					order.provider.name = prov.name;
					order.provider.id = supply.provider.id;
					LocalDate date = LocalDate.now();
					order.orderedDate = date;
					order.status = "PENDING";
					ordersToGenerate.add(order);
				}
			}
		}

		for (OrderDto order: ordersToGenerate) {
			og.insertOrder(DtoMapper.toRecordOrIns(order));
		}
		
		return ordersToGenerate;
		
	}

}
