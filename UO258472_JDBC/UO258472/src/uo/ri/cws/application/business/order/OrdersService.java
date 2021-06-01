package uo.ri.cws.application.business.order;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

public interface OrdersService {

	/**
	 * Generates orders following the statement of the problem
	 * @return the list of new order dto generated
	 * @throws BusinessException DOES NOT
	 */
	List<OrderDto> generateOrders() throws BusinessException;

	/**
	 * Returns all the orders for the provider identified by its nif
	 * @param nif
	 * @return a list with orders or empty if there is no order from the provider
	 * @throws BusinessException DOES NOT
	 */
	List<OrderDto> findByProviderNif(String nif) throws BusinessException;

	/**
	 * @param code
	 * @return the order identified by the code or Optional.empty()
	 * 		if does not exist
	 * @throws BusinessException DOES NOT
	 */
	Optional<OrderDto> findByCode(String code) throws BusinessException;

	/**
	 * Updates prices and stocks of all involved spare parts and updates the
	 * order reception date to today and its status is changed to RECEIVED
	 * @param code
	 * @return the order dto with the updated status
	 * @throws BusinessException in the following cases:
	 * 	- the order does not exist
	 *  - the order is not in status PENDING
	 */
	OrderDto receive(String code) throws BusinessException;

}
