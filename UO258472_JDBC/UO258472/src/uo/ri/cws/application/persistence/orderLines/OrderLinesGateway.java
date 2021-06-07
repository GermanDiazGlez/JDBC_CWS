package uo.ri.cws.application.persistence.orderLines;

import java.sql.SQLException;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;

public interface OrderLinesGateway extends Gateway<OrderLinesRecord> {

	/**
	 * Return a list from OrderLinesRecord that matches with the id_order
	 * @param id_order
	 * @return OrderLinesRecord list
	 */
	List<OrderLinesRecord> getLinesForOrder(String id_order) throws SQLException;
	
}
