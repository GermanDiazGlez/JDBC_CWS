package uo.ri.cws.application.persistence.substitution.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.substitution.SubstitutionGateway;
import uo.ri.cws.application.persistence.substitution.SubstitutionRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class SubstitutionGatewayImpl implements SubstitutionGateway{

//	@Override
//	public List<SparePartRecord> findAll() throws SQLException {
//		Connection c = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		List<SparePartRecord> parts = null;
//
//		try {
//			c = Jdbc.getCurrentConnection();
//
//			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_ALL"));
//
//			rs = pst.executeQuery();
//
//			parts = RecordAssembler.toSparePartRecordList(rs);
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		finally {
//			Jdbc.close(pst);
//		}
//		return parts;
//	}

	@Override
	public Optional<SubstitutionRecord> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SubstitutionRecord> supp = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_SPAREPART_BY_ID"));
			pst.setString(1, id);
			rs = pst.executeQuery();

			pst.setString(1, id);

			rs = pst.executeQuery();

			supp = rs.next() ? Optional.of(RecordAssembler.toSubstitutionRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return supp;
	}

	@Override
	public void add(SubstitutionRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SubstitutionRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SubstitutionRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SubstitutionRecord> findBySparePartId(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SubstitutionRecord> supp = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUBSTITUTIONS_FIND_SPAREPART_BY_ID"));
			pst.setString(1, id);
			rs = pst.executeQuery();

			supp = rs.next() ? Optional.of(RecordAssembler.toSubstitutionRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return supp;
	}

}
