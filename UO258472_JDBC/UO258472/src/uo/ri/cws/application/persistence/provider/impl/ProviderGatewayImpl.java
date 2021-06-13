package uo.ri.cws.application.persistence.provider.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.provider.ProviderRecord;
import uo.ri.cws.application.persistence.supply.SupplyRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class ProviderGatewayImpl implements ProviderGateway {

	@Override
	public void add(ProviderRecord t) throws SQLException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_ADD"));

		pst.setString(1, t.id);
		pst.setString(2, t.email);
		pst.setString(3, t.name);
		pst.setString(4, t.nif);
		pst.setString(5, t.phone);
		pst.setLong(6, 1L);

		pst.executeUpdate();

	}

	@Override
	public void remove(String nif) throws SQLException, BusinessException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_DELETE"));

		pst.setString(1, nif);

		pst.executeUpdate();
		
	}

	@Override
	public void update(ProviderRecord t) throws SQLException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_UPDATE"));

		pst.setString(1, t.name);
		pst.setString(2, t.email);
		pst.setString(3, t.phone);
		pst.setString(4, t.nif);

		pst.executeUpdate();
		
	}

	@Override
	public Optional<ProviderRecord> findById(String id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_BY_ID"));

		pst.setString(1, id);

		rs = pst.executeQuery();

		prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();

		return prov;
	}

	@Override
	public List<ProviderRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProviderRecord> findProviderByNif(String nif) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_BY_NIF"));

		pst.setString(1, nif);

		rs = pst.executeQuery();

		prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();

		return prov;
	}

	@Override
	public Optional<ProviderRecord> findProviderNameByOrderCode(String code) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;
			
		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_BY_ORDER_CODE"));

		pst.setString(1, code);

		rs = pst.executeQuery();

		prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();

		return prov;
	}

	@Override
	public Optional<ProviderRecord> findProviderNifByName(String name) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;
			
		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_NIF_BY_NAME"));

		pst.setString(1, name);

		rs = pst.executeQuery();

		prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();

		return prov;
	}

	@Override
	public Optional<ProviderRecord> findProviderByOthers(String name, String email, String phone) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<ProviderRecord> prov = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_PROVIDER_BY_OTHERS"));

		pst.setString(1, name);
		pst.setString(2, email);
		pst.setString(3, phone);

		rs = pst.executeQuery();

		prov = rs.next() ? Optional.of(RecordAssembler.toProviderRecord(rs)) : Optional.empty();

		return prov;
	}

	@Override
	public List<ProviderRecord> findProviderByName(String name) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<ProviderRecord> providers = new ArrayList<>();

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TPROVIDERS_FIND_PROVIDERS_BY_NAME"));

		String name1 = "%" + name + "%";

		pst.setString(1, name1);

		rs = pst.executeQuery();

		while (rs.next()){
			providers.add(RecordAssembler.toProviderRecord(rs));
		}

		return providers;
	}

}
