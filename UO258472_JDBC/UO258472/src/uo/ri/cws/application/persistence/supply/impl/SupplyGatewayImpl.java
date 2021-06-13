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
	public Optional<SupplyRecord> findById(String id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SupplyRecord> supp = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_BY_ID"));

		pst.setString(1, id);

		rs = pst.executeQuery();

		supp = rs.next() ? Optional.of(RecordAssembler.toSupplyRecord(rs)) : Optional.empty();

		return supp;
	}

	@Override
	public void add(SupplyRecord t) throws SQLException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_ADD"));

		pst.setString(1, t.id);
		pst.setInt(2, t.deliveryTerm);
		pst.setDouble(3, t.price);
		pst.setLong(4, t.version);
		pst.setString(5, t.providerId);
		pst.setString(6, t.sparePartId);

		pst.executeUpdate();

	}

	@Override
	public void remove(String id) throws SQLException, BusinessException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_DELETE"));

		pst.setString(1, id);

		pst.executeUpdate();
	}

	@Override
	public void update(SupplyRecord t) throws SQLException {
		PreparedStatement pst = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_UPDATE"));

		pst.setDouble(1, t.price);
		pst.setInt(2, t.deliveryTerm);
		pst.setString(3, t.id);

		pst.executeUpdate();
		
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

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_SUPLY_BY_SPAREPART_ID"));
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

	@Override
	public Optional<SupplyRecord> findProviderById(String id) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SupplyRecord> supp = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_PROVIDER"));
		pst.setString(1, id);
		rs = pst.executeQuery();

		supp = rs.next() ? Optional.of(RecordAssembler.toSupplyRecord(rs)) : Optional.empty();

		return supp;
	}

	@Override
	public List<SupplyRecord> findProvidersBySparePartId(String sparePartId) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SupplyRecord> providers = new ArrayList<>();

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_PROVIDERS_BY_SPAREPART_ID"));

		pst.setString(1, sparePartId);

		rs = pst.executeQuery();

		while (rs.next()){
			providers.add(RecordAssembler.toSupplyRecord(rs));
		}

		return providers;
	}

	@Override
	public Optional<SupplyRecord> findSupplyByIdProviderAndIdSparePart(String idProvider, String idSparePart) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SupplyRecord> supp = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_BY_PROVIDER_AND_SPAREPART"));
		pst.setString(1, idProvider);
		pst.setString(2, idSparePart);
		rs = pst.executeQuery();

		supp = rs.next() ? Optional.of(RecordAssembler.toSupplyRecord(rs)) : Optional.empty();

		return supp;
	}

    @Override
    public List<SupplyRecord> findSparePartIdsByIdProvider(String providerId) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SupplyRecord> supplies = new ArrayList<>();

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TSUPPLIES_FIND_SPAREPART_IDS_BY_PROVIDER_ID"));

		pst.setString(1, providerId);

		rs = pst.executeQuery();

		while (rs.next()){
			supplies.add(RecordAssembler.toSupplyRecord(rs));
		}

		return supplies;
    }

}
