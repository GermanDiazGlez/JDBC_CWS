package uo.ri.cws.application.persistence.supply.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.supply.SupplyGateway;
import uo.ri.cws.application.persistence.supply.SupplyRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class SupplyGatewayImpl implements SupplyGateway {

	@Override
	public Optional<SupplyRecord> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SupplyRecord> supp = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_BY_ID"));
			pst.setString(1, id);
			rs = pst.executeQuery();

			pst.setString(1, id);

			rs = pst.executeQuery();

			supp = rs.next() ? Optional.of(RecordAssembler.toSupplyRecord(rs)) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return supp;
	}

	@Override
	public void add(SupplyRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String id) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SupplyRecord t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SupplyRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SupplyRecord> findBySparePartId(String id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SupplyRecord> supp = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_SPAREPART_BY_ID"));
		pst.setString(1, id);
		rs = pst.executeQuery();

		supp = rs.next() ? Optional.of(RecordAssembler.toSupplyRecord(rs)) : Optional.empty();

		return supp;
	}

	@Override
	public List<SupplyRecord> findBestProviderBySparePartId(String id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SupplyRecord> providers = new ArrayList<>();

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_BEST_PROVIDER_BY_SPAREPART_ID"));

		pst.setString(1, id);

		rs = pst.executeQuery();

		while (rs.next()){
			providers.add(RecordAssembler.toSupplyRecord(rs));
		}

		return providers;
	}

}
