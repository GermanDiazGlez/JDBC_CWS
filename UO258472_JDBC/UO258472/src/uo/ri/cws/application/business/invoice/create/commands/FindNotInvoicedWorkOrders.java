//package uo.ri.cws.application.business.invoice.create.commands;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import alb.util.assertion.Argument;
//import uo.ri.cws.application.business.BusinessException;
//import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
//import uo.ri.cws.application.business.util.DtoMapper;
//import uo.ri.cws.application.business.util.command.Command;
//import uo.ri.cws.application.persistence.PersistenceFactory;
//import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
//
///**
// * Business to list all not invoiced work orders.
// *
// */
//public class FindNotInvoicedWorkOrders implements Command<List<InvoicingWorkOrderDto>> {
//
//    private static final String prefix = "FIND NOT INVOICED WORK ORDERS:: ";
//
//    private WorkOrderGateway workOrderGateway = PersistenceFactory.forWorkOrder();
//
//    private String dni = null;
//
//    public FindNotInvoicedWorkOrders(String dni) {
//
//        //Validations
//        Argument.isNotNull(dni, prefix + "Client dni to look for cannot be null");
//        Argument.isNotEmpty(dni, prefix + "Client dni to look for cannot be empty");
//
//        this.dni = dni;
//    }
//
////    public List<InvoicingWorkOrderDto> execute() throws BusinessException, SQLException {
////
////        List<InvoicingWorkOrderDto> invoices = DtoMapper.toDto(workOrderGateway.findNotInvoiced(dni));
////
////
////        if (invoices.size() == 0)
////            throw new BusinessException("There was no invoice for a client with such an id");
////
////        return invoices;
////
////    }
//
//}