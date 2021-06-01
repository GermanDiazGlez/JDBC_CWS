package uo.ri.cws.application.persistence.sparePart.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.persistence.sparePart.SparePartReportGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartReportRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class SparePartReportGatewayImpl implements SparePartReportGateway {
	@Override
	public Optional<SparePartReportRecord> findByCode(String code) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		Optional<SparePartReportRecord> part = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_FIND_BY_CODE"));

			pst.setString(1, code);

			rs = pst.executeQuery();

			part = rs.next() ? Optional.of(RecordAssembler.toSparePartReportRecordWithOutUnitsSold(rs)) : Optional.empty();

			pst2 = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_GET_VENTAS_BY_SPAREPART_ID"));

			pst2.setString(1, part.get().id);

			rs2 = pst2.executeQuery();

			rs2.next();

			part.get().totalUnitsSold = rs2.getInt("ventas");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return part;
	}

	@Override
	public Optional<SparePartReportRecord> isPresent(String code) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Optional<SparePartReportRecord> part = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_FIND_BY_CODE"));

			pst.setString(1, code);

			rs = pst.executeQuery();

			part = rs.next() ? Optional.of(RecordAssembler.toSparePartReportRecordWithOutUnitsSold(rs)) : Optional.empty();

			System.out.println(part);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return part;
	}

	@Override
	public void add(SparePartReportRecord sparePartReportRecord) throws SQLException {

	}

	@Override
	public void remove(String id) throws SQLException, BusinessException {

	}

	@Override
	public void update(SparePartReportRecord sparePartReportRecord) throws SQLException {

	}

	@Override
	public Optional<SparePartReportRecord> findById(String id) {
		return Optional.empty();
	}

	@Override
	public List<SparePartReportRecord> findByDescription(String description) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		List<SparePartReportRecord> parts = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_FIND_BY_DESCRIPTION"));

			String description1 = "%" + description + "%";

			pst.setString(1, description1);

			pst2 = c.prepareStatement(Conf.getInstance().getProperty("TSPAREPARTS_GET_VENTAS_BY_SPAREPART_ID"));

			rs = pst.executeQuery();

			parts = RecordAssembler.toSparePartReportRecordListWithOutSolds(rs);

			while(rs.next()){
				SparePartReportRecord part = new SparePartReportRecord();
				pst2.setString(1, rs.getString("id"));
				rs2 = pst2.executeQuery();
				while(rs2.next()){
					part.totalUnitsSold = rs2.getInt("ventas");
					parts.add(part);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		return parts;
	}

	@Override
	public List<SparePartReportRecord> findAll() throws SQLException {
		return null;
	}

}
