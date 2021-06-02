package uo.ri.cws.application.persistence.sparePart.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class  SparePartGatewayImpl implements SparePartGateway {

	@Override
	public void add(SparePartRecord sp) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_ADD"));

			pst.setString(1, sp.id);
			pst.setLong(2, sp.version);
			pst.setString(3, sp.code);
			pst.setString(4, sp.description);
			pst.setInt(5, sp.stock);
			pst.setInt(6, sp.minStock);
			pst.setInt(7, sp.maxStock);
			pst.setDouble(8, sp.price);

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void remove(String code) throws SQLException, BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_REMOVE"));
			pst.setString(1, code);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}

	}

	@Override
	public void update(SparePartRecord t) throws SQLException {
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_UPDATE"));
			pst.setString(1, t.description);
			pst.setInt(2, t.stock);
			pst.setInt(3, t.minStock);
			pst.setInt(4, t.maxStock);
			pst.setDouble(5, t.price);
			pst.setString(6, t.code);

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}

	}

	@Override
	public List<SparePartRecord> findAll() throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SparePartRecord> parts = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_FIND_ALL"));

			rs = pst.executeQuery();

			parts = RecordAssembler.toSparePartRecordList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return parts;
	}

	@Override
	public Optional<SparePartRecord> findByCode(String code) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SparePartRecord> part = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_GET_BY_CODE"));

			pst.setString(1, code);

			rs = pst.executeQuery();

			part = rs.next() ? Optional.of(RecordAssembler.toSparePartRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return part;
	}

	@Override
	public Optional<SparePartRecord> findById(String id){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SparePartRecord> part = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_FIND_BY_ID"));

			pst.setString(1, id);

			rs = pst.executeQuery();

			part = rs.next() ? Optional.of(RecordAssembler.toSparePartRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return part;
	}

	@Override
	public Optional<SparePartRecord> isPresent(String code) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SparePartRecord> part = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_GET_BY_CODE"));

			pst.setString(1, code);

			rs = pst.executeQuery();

			part = rs.next() ? Optional.of(RecordAssembler.toSparePartRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return part;
	}

}
