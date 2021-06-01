package uo.ri.cws.application.persistence.mechanic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.MechanicRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class MechanicGatewayImpl implements MechanicGateway{

	@Override
	public void add(MechanicRecord mechanic) throws SQLException {
		// Process
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_ADD"));

			pst.setString(1, mechanic.id);
			pst.setString(2, mechanic.dni);
			pst.setString(3, mechanic.name);
			pst.setString(4, mechanic.surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void remove(String idMechanic) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_REMOVE"));
			pst.setString(1, idMechanic);

			pst.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}

	}

	@Override
	public void update(MechanicRecord t) throws SQLException {
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_UPDATE"));
			pst.setString(1, t.name);
			pst.setString(2, t.surname);
			pst.setString(3, t.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}

	}

	@Override
	public Optional<MechanicRecord> findById(String id) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<MechanicRecord> mechanic = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FIND_BY_ID"));

			pst.setString(1, id);

			rs = pst.executeQuery();

			mechanic = rs.next() ? Optional.of(RecordAssembler.toMechanicRecord(rs)) : Optional.empty();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return mechanic;
	}

	@Override
	public List<MechanicRecord> findAll() throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MechanicRecord> mechanics = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FIND_ALL"));

			rs = pst.executeQuery();

			mechanics = RecordAssembler.toMechanicRecordList(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return mechanics;
	}

	@Override
	public Optional<MechanicRecord> findByDni(String dni) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<MechanicRecord> mechanic = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYDNI"));

			pst.setString(1, dni);

			rs = pst.executeQuery();

			mechanic = rs.next() ? Optional.of(RecordAssembler.toMechanicRecord(rs)) : Optional.empty();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return mechanic;
	}

}
