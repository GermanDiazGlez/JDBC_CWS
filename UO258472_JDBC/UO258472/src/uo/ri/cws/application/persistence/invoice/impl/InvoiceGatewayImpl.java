package uo.ri.cws.application.persistence.invoice.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.util.Conf;

public class InvoiceGatewayImpl implements InvoiceGateway {

	@Override
	public void add(InvoiceRecord iR) throws SQLException {
		PreparedStatement pst = null;
		String idInvoice;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TINVOICES_INSERT"));
		pst.setString(1, iR.id);
		pst.setLong(2, iR.number);
		pst.setDate(3, java.sql.Date.valueOf(iR.date));
		pst.setDouble(4, iR.vat);
		pst.setDouble(5, iR.total);
		pst.setString(6, "NOT_YET_PAID");
		pst.setInt(7, 1);

		pst.executeUpdate();
	}

	@Override
	public void remove(String id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(InvoiceRecord t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<InvoiceRecord> findById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceRecord> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InvoiceRecord> findByNumber(Long number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNextInvoiceNumber() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = Jdbc.getCurrentConnection().prepareStatement(Conf.getInstance().getProperty("TINVOICES_GET_NEXT_INVOICE_NUMBER"));

		rs = pst.executeQuery();

		if(rs.next()){
			return rs.getLong(1) + 1;
		} else {
			return 1L;
		}
	}

}
