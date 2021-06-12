package uo.ri.cws.application.persistence.order.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.order.OrderGateway;
import uo.ri.cws.application.persistence.order.OrderRecord;
import uo.ri.cws.application.persistence.order.OrderToGenerateRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class OrderGatewayImpl implements OrderGateway{

	@Override
	public Optional<OrderRecord> findById(String id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<OrderRecord> order = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_FIND_BY_ID"));
		pst.setString(1, id);
		rs = pst.executeQuery();

		order = rs.next() ? Optional.of(RecordAssembler.toOrderRecord(rs)) : Optional.empty();

		return order;
	}


	@Override
	public Optional<OrderRecord> findBySparePartCode(String code) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<OrderRecord> order = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_FIND_SPAREPART_BY_CODE"));
		pst.setString(1, code);
		rs = pst.executeQuery();

		order = rs.next() ? Optional.of(RecordAssembler.toOrderRecord(rs)) : Optional.empty();

		return order;
	}


	@Override
	public void add(OrderRecord t) throws SQLException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_ADD"));

		pst.setString(1, t.code);
		pst.setString(2, t.id);
		pst.setDate(3, Date.valueOf(t.orderedDate));
		pst.setDouble(4, t.amount);
		pst.setString(5, t.providerId);
		pst.setString(6, t.status);
		pst.setLong(7, t.version);

		pst.executeUpdate();
	}


	@Override
	public void remove(String id) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(OrderRecord t) throws SQLException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_UPDATE"));

		pst.setDouble(1, t.amount);
		pst.setString(2, t.id);

		pst.executeUpdate();
		
	}


	@Override
	public List<OrderRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<OrderRecord> findByCode(String code) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<OrderRecord> order;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_FIND_BY_CODE"));

		pst.setString(1, code);

		rs = pst.executeQuery();

		order = rs.next() ? Optional.of(RecordAssembler.toOrderRecord(rs)) : Optional.empty();

		return order;
	}


	@Override
	public List<OrderRecord> findOrdersByProviderNif(String providerId) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderRecord> orders = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_FIND_BY_PROVIDER_ID"));

		pst.setString(1, providerId);

		rs = pst.executeQuery();

		orders = RecordAssembler.toOrderRecordList(rs);

		return orders;
	}


	@Override
	public List<OrderToGenerateRecord> getOrdersToGenerate() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderToGenerateRecord> orders = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_GET_ORDERS_TO_GENERATE"));

		rs = pst.executeQuery();

		orders = RecordAssembler.toOrderToGenerateRecordList(rs);

		return orders;
	}

	@Override
	public void insertOrder(OrderToGenerateRecord order) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_INSERT_ORDER"));

		pst.setString(1, order.id);
		pst.setDouble(2, order.price);
		pst.setString(3, order.code);
		Date date1 = java.sql.Date.valueOf(order.orderedDate);
		pst.setDate(4, date1);
		pst.setDate(5, null);
		pst.setString(6, order.status);
		pst.setLong(7, 1);
		pst.setString(8, order.providerId);

		pst.executeUpdate();

	}

	@Override
	public void updateStatusAndDate(OrderRecord t) throws SQLException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERS_UPDATE_STATUS"));

		pst.setString(1, t.status);
		Date date1 = java.sql.Date.valueOf(t.receptionDate);
		pst.setDate(2, date1);
		pst.setString(3, t.id);

		pst.executeUpdate();

	}

}
