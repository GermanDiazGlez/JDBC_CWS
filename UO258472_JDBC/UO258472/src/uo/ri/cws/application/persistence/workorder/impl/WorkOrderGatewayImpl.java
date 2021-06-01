package uo.ri.cws.application.persistence.workorder.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class WorkOrderGatewayImpl implements WorkOrderGateway {

	@Override
	public void add(WorkOrderRecord t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(WorkOrderRecord t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<WorkOrderRecord> findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderRecord> findByIds(List<String> workOrderIds) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderRecord> findByVehicleId(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderRecord> findByMechanicId(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderRecord> findByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
