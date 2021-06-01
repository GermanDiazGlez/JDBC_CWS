package uo.ri.cws.application.persistence.order;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface OrderGateway extends Gateway<OrderRecord> {

	/**
	 * Return the OrderRecord that corresponds with the code
	 * @param code
	 * @return OrderRecord or null if none
	 */
	Optional<OrderRecord> findByCode(String code);

	/**
	 * Return the OrderRecord that corresponds with the SparePartCode
	 * @param code
	 * @return OrderRecord or null if none
	 */
	Optional<OrderRecord> findBySparePartCode(String code);

	/**
	 * Return a list of OrderRecord which correspond with the providerNif
	 * @param providerNIF
	 * @return OrderRecord list
	 */
	List<OrderRecord> findOrdersByProviderNif(String providerNIF);

	/**
	 * Return a list of OrderToGenerateRecord that have less stock than the minStock
	 * @return OrderToGenerateRecord list
	 */
	List<OrderToGenerateRecord> getOrdersToGenerate();

	/**
	 * Method that inserts the orders generated
	 * @param order
	 */
	void insertOrder(OrderToGenerateRecord order);
}
