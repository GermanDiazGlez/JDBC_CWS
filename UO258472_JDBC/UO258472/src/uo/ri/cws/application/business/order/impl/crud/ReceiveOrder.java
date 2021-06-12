package uo.ri.cws.application.business.order.impl.crud;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrderDto.OrderLineDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.order.OrderGateway;
import uo.ri.cws.application.persistence.orderLines.OrderLinesGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartRecord;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReceiveOrder implements Command<OrderDto> {
    private String code;

    public ReceiveOrder(String code){
        super();
        Argument.isTrue(code.trim().length()!=0, "The code cant be a blank");
        this.code = code;
    }

    @Override
    public OrderDto execute() throws BusinessException, SQLException {
        OrderGateway og = PersistenceFactory.forOrders();
        SparePartGateway spg = PersistenceFactory.forSparePart();
        OrderLinesGateway olg = PersistenceFactory.forOrderLines();
        BusinessCheck.isTrue(og.findByCode(code).isPresent(), "That code is not an order code");
        OrderDto order = DtoMapper.toDtoOr(og.findByCode(code)).get();

        List<OrderLineDto> lines = DtoMapper.toDtoListOrdLin(olg.getLinesForOrder(order.id));

        for (OrderLineDto ol : lines) {
            SparePartRecord sparePart =spg.findById(ol.sparePart.id).get();

            int previousStock = sparePart.stock;
            double previousPrice = sparePart.price;
            int maxStock = sparePart.stock + ol.quantity;
            double newPrice = (previousPrice * previousStock + ol.quantity * ol.price * 1.2) / (maxStock);
            sparePart.price = newPrice;
            sparePart.stock += ol.quantity;

            spg.update(sparePart);
        }

        order.receptionDate = LocalDate.now();
        order.status = "RECEIVED";

        og.updateStatusAndDate(DtoMapper.toRecord(order));

        return null;
    }


}
