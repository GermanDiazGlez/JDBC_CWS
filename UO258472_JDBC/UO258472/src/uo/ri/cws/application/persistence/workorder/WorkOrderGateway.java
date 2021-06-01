package uo.ri.cws.application.persistence.workorder;

import java.sql.SQLException;
import java.util.List;

import uo.ri.cws.application.persistence.Gateway;

public interface WorkOrderGateway extends Gateway<WorkOrderRecord>{

	/**
	 * @param workorder Ids, list of workorders to retrieve
	 * @return list of workorder dto whose id is included in the parameter, probably empty
	 * @throws SQLException 
	 */
	List<WorkOrderRecord> findByIds(List<String> workOrderIds) throws SQLException;
	
	/**
	 * @return a list of all work orders for an specific vehicle id
	 * @throws SQLException 
	 */
	List<WorkOrderRecord> findByVehicleId(String id) throws SQLException;

	/**
	 * @param a mechanic id
	 * @return a list of all work orders assigned to this specific mechanic
	 * @throws SQLException 
	 */
	List<WorkOrderRecord> findByMechanicId(String id) throws SQLException;

	/**
	 * @param the state
	 * @return a list of all work orders in the specific state
	 * 
	 */
	List<WorkOrderRecord> findByStatus(String status);
}