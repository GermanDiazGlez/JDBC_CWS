package uo.ri.cws.application.persistence.invoice.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		Connection c = null;
		PreparedStatement pst = null;
		String idInvoice;

		try {
			c = Jdbc.getCurrentConnection();
			idInvoice = UUID.randomUUID().toString();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TINVOICES_INSERT"));
			pst.setString(1, idInvoice);
			pst.setLong(2, iR.number);
			pst.setDate(3, java.sql.Date.valueOf(iR.date));
			pst.setDouble(4, iR.vat);
			pst.setDouble(5, iR.total);
			pst.setString(6, "NOT_YET_PAID");

			pst.executeUpdate();

		} finally {
			Jdbc.close(pst);
		}

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
		// TODO Auto-generated method stub
		return null;
	}

}
