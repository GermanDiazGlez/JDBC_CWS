package uo.ri.cws.application.business.invoice.create;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.invoice.PaymentMeanDto;
import uo.ri.cws.application.business.invoice.create.commands.CreateInvoice;
//import uo.ri.cws.application.business.invoice.create.commands.FindNotInvoicedWorkOrders;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class InvoicingServiceImpl implements InvoicingService {

	CommandExecutor executor = new CommandExecutor();

	@Override
	public InvoiceDto createInvoiceFor(List<String> workOrderIds) throws BusinessException {
		return executor.execute(new CreateInvoice(workOrderIds));
	}

	@Override
	public List<InvoicingWorkOrderDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		//return executor.execute(new FindNotInvoicedWorkOrders(dni));
		return null;
	}

	@Override
	public Optional<InvoiceDto> findInvoice(Long number) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanDto> findPayMeansByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(String invoiceId, Map<Long, Double> charges) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
