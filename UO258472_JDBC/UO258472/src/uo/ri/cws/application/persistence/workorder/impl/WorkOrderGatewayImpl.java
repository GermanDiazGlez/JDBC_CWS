package uo.ri.cws.application.persistence.workorder.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;
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
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_UPDATE"));

		if (t.invoiceId == null)
			t.invoiceId = findById(t.id).get().invoiceId;

		if (t.status == null)
			t.status = findById(t.id).get().status;

		pst.setString(1, t.invoiceId);
		pst.setString(2, t.status);
		pst.setString(3, t.id);

		pst.executeUpdate();

	}

	@Override
	public Optional<WorkOrderRecord> findById(String id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		Optional<WorkOrderRecord> record = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FIND_WORKORDERS"));

		pst.setString(1, id);

		rs = pst.executeQuery();

		record = rs.next() ? Optional.of(RecordAssembler.toWorkOrderRecord(rs)) : Optional.empty();

		return record;
	}

	@Override
	public List<WorkOrderRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderRecord> findByIds(List<String> workOrderIds) throws SQLException {
		List<WorkOrderRecord> listRecord = new ArrayList<>();

		for (String workOrderID : workOrderIds) {
			listRecord.add(findById(workOrderID).get());
		}

		return listRecord;
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

	@Override
	public List<WorkOrderRecord> findNotInvoiced(String dni) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<WorkOrderRecord> invoices = new ArrayList<>();

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FIND_NOT_INVOICED"));

		pst.setString(1, dni);

		rs = pst.executeQuery();

		invoices = RecordAssembler.toWorkOrderInvoiceRecordList(rs);

		return invoices;
	}

}
