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

public class InvoicingServiceImpl implements InvoicingService {

	@Override
	public InvoiceDto createInvoiceFor(List<String> workOrderIds) throws BusinessException {
		CreateInvoice ci = new CreateInvoice(workOrderIds);
		return ci.execute();
	}

	@Override
	public List<InvoicingWorkOrderDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
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
	
//	@Override
//	public InvoiceDto createInvoice(InvoiceRecord invoice) throws BusinessException {
//		CreateInvoice ci = new CreateInvoice(invoice);
//		return ci.createInvoice(invoice);
//	}

}
