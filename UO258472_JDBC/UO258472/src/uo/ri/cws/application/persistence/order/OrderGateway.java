package uo.ri.cws.application.persistence.order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface OrderGateway extends Gateway<OrderRecord> {

	/**
	 * Return the OrderRecord that corresponds with the code
	 * @param code
	 * @return OrderRecord or null if none
	 */
	Optional<OrderRecord> findByCode(String code) throws SQLException;

	/**
	 * Return the OrderRecord that corresponds with the SparePartCode
	 * @param code
	 * @return OrderRecord or null if none
	 */
	Optional<OrderRecord> findBySparePartCode(String code) throws SQLException;

	/**
	 * Return a list of OrderRecord which correspond with the providerNif
	 * @param providerNIF
	 * @return OrderRecord list
	 */
	List<OrderRecord> findOrdersByProviderNif(String providerNIF) throws SQLException;

	/**
	 * Return a list of OrderToGenerateRecord that have less stock than the minStock
	 * @return OrderToGenerateRecord list
	 */
	List<OrderToGenerateRecord> getOrdersToGenerate() throws SQLException;

	/**
	 * Method that inserts the orders generated
	 * @param order
	 */
	void insertOrder(OrderToGenerateRecord order) throws SQLException;

	/**
	 * Updates the status of the order
	 * @param t
	 */
	void updateStatusAndDate(OrderRecord t) throws SQLException;
}
