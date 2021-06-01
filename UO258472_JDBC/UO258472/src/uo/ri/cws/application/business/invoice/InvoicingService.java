package uo.ri.cws.application.business.invoice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Cashier It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface InvoicingService {

	/**
	 * Create a new invoice billing the workorders in the argument
	 * The new invoice will be in NOT_YET_PAID status, the workorders will be marked
	 * as INVOICED
	 * @param the list of workorder ids to bill
	 * @throws BusinessException if
	 * 	- the status of any of the workorders is not FINISHED 
	 *  - any of the workorders does not exist
	 *  - status Is already settled 
	 */
	InvoiceDto createInvoiceFor(List<String> workOrderIds)
			throws BusinessException;


	List<InvoicingWorkOrderDto> findWorkOrdersByClientDni(String dni)
			throws BusinessException;

	Optional<InvoiceDto> findInvoice(Long number) throws BusinessException;

	List<PaymentMeanDto> findPayMeansByClientDni(String dni)
			throws BusinessException;

	void settleInvoice(String invoiceId, Map<Long, Double> charges)
			throws BusinessException;
	
}
