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
	public Optional<OrderRecord> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<OrderRecord> order = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TORDERS_FIND_BY_ID"));
			pst.setString(1, id);
			rs = pst.executeQuery();

			order = rs.next() ? Optional.of(RecordAssembler.toOrderRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return order;
	}


	@Override
	public Optional<OrderRecord> findBySparePartCode(String code) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<OrderRecord> order = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TORDERS_FIND_SPAREPART_BY_CODE"));
			pst.setString(1, code);
			rs = pst.executeQuery();

			order = rs.next() ? Optional.of(RecordAssembler.toOrderRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return order;
	}


	@Override
	public void add(OrderRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void remove(String id) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(OrderRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<OrderRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<OrderRecord> findByCode(String code) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<OrderRecord> order;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TORDERS_FIND_BY_CODE"));
			
			pst.setString(1, code);

			rs = pst.executeQuery();

			order = rs.next() ? Optional.of(RecordAssembler.toOrderRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return order;
	}


	@Override
	public List<OrderRecord> findOrdersByProviderNif(String providerId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderRecord> orders = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TORDERS_FIND_BY_PROVIDER_ID"));
			
			pst.setString(1, providerId);

			rs = pst.executeQuery();

			orders = RecordAssembler.toOrderRecordList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return orders;
	}


	@Override
	public List<OrderToGenerateRecord> getOrdersToGenerate() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderToGenerateRecord> orders = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TORDERS_GET_ORDERS_TO_GENERATE"));

			rs = pst.executeQuery();
			
			orders = RecordAssembler.toOrderToGenerateRecordList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return orders;
	}

	@Override
	public void insertOrder(OrderToGenerateRecord order) {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TORDERS_INSERT_ORDER"));

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

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

}
