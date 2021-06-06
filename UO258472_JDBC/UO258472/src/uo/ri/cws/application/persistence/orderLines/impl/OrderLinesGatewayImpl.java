package uo.ri.cws.application.persistence.orderLines.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.orderLines.OrderLinesGateway;
import uo.ri.cws.application.persistence.orderLines.OrderLinesRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class OrderLinesGatewayImpl implements OrderLinesGateway{

	@Override
	public void add(OrderLinesRecord t) throws SQLException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TORDERLINES_ADD"));

		pst.setString(1, t.sparepart_id);
		pst.setString(2, t.order_id);
		pst.setDouble(3, t.price);
		pst.setInt(4, t.quantity);

		pst.executeUpdate();
	}

	@Override
	public void remove(String id) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(OrderLinesRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<OrderLinesRecord> findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderLinesRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderLinesRecord> getLinesForOrder(String id_order) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderLinesRecord> lines = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TORDERLINES_GET_LINES"));
			
			pst.setString(1, id_order);

			rs = pst.executeQuery();

			lines = RecordAssembler.toOrderLinesRecordList(rs);

			System.out.println(lines);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return lines;
	}

	
}
