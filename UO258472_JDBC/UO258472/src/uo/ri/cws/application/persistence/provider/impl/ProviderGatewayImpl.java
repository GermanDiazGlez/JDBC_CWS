package uo.ri.cws.application.persistence.provider.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.provider.ProviderRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class ProviderGatewayImpl implements ProviderGateway {

	@Override
	public void add(ProviderRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProviderRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<ProviderRecord> findById(String id) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_BY_ID"));

			pst.setString(1, id);

			rs = pst.executeQuery();

			prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return prov;
	}

	@Override
	public List<ProviderRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProviderRecord> findProviderByNif(String nif) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_BY_NIF"));

			pst.setString(1, nif);

			rs = pst.executeQuery();

			prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return prov;
	}

	@Override
	public Optional<ProviderRecord> findProviderNameByOrderCode(String code) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_BY_ORDER_CODE"));

			pst.setString(1, code);

			rs = pst.executeQuery();

			prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return prov;
	}

	@Override
	public Optional<ProviderRecord> findProviderNifByName(String name) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;

		try {
			c = Jdbc.getCurrentConnection();
			
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_NIF_BY_NAME"));

			pst.setString(1, name);

			rs = pst.executeQuery();

			prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return prov;
	}

}
