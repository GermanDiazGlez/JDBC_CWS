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
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TMECHANICS_ADD"));

		pst.setString(1, mechanic.id);
		pst.setString(2, mechanic.dni);
		pst.setString(3, mechanic.name);
		pst.setString(4, mechanic.surname);

		pst.executeUpdate();
	}

	@Override
	public void remove(String idMechanic) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TMECHANICS_REMOVE"));
		pst.setString(1, idMechanic);

		pst.executeUpdate();
	}

	@Override
	public void update(MechanicRecord t) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TMECHANICS_UPDATE"));
		pst.setString(1, t.name);
		pst.setString(2, t.surname);
		pst.setString(3, t.id);

		pst.executeUpdate();
	}

	@Override
	public Optional<MechanicRecord> findById(String id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<MechanicRecord> mechanic = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FIND_BY_ID"));

		pst.setString(1, id);

		rs = pst.executeQuery();

		mechanic = rs.next() ? Optional.of(RecordAssembler.toMechanicRecord(rs)) : Optional.empty();

		return mechanic;
	}

	@Override
	public List<MechanicRecord> findAll() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MechanicRecord> mechanics = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FIND_ALL"));

		rs = pst.executeQuery();

		mechanics = RecordAssembler.toMechanicRecordList(rs);

		return mechanics;
	}

	@Override
	public Optional<MechanicRecord> findByDni(String dni) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<MechanicRecord> mechanic = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TMECHANICS_FINDBYDNI"));

		pst.setString(1, dni);

		rs = pst.executeQuery();

		mechanic = rs.next() ? Optional.of(RecordAssembler.toMechanicRecord(rs)) : Optional.empty();

		return mechanic;
	}

}
