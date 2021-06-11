package uo.ri.cws.application.business.invoice.create.commands;

import java.sql.SQLException;
import java.util.List;

import alb.util.assertion.Argument;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.util.DtoMapper;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

/**
 * Business to list all not invoiced work orders.
 *
 */
public class FindNotInvoicedWorkOrders implements Command<List<InvoicingWorkOrderDto>> {
    private String dni = null;
    private WorkOrderGateway wog = PersistenceFactory.forWorkOrder();

    public FindNotInvoicedWorkOrders(String dni) {
        Argument.isNotNull(dni, "The dni cant be null");
        Argument.isTrue(dni.trim().length()!=0, "The dni cant be empty");
        this.dni = dni;
    }

    public List<InvoicingWorkOrderDto> execute() throws BusinessException, SQLException {

        List<InvoicingWorkOrderDto> invoices = DtoMapper.toDtoListInvoice(wog.findNotInvoiced(dni));

        for (InvoicingWorkOrderDto i : invoices) {
            System.out.println("Fecha final : " + i.date);
        }

        if (invoices.size() == 0)
            throw new BusinessException("There was no invoice for a client with such an id");

        return invoices;

    }

}