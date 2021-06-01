package uo.ri.cws.application.ui.cashier.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;

public class FindNotInvoicedWorkOrdersAction implements Action {


	/**
	 * Process:
	 * 
	 *   - Ask customer dni
	 *    
	 *   - Display all uncharged workorder  
	 *   		(status <> 'INVOICED'). For each workorder, display 
	 *   		id, vehicle id, date, status, amount and description
	 */

	private static String SQL =
		"select a.id, a.description, a.date, a.status, a.amount " +
		"from TWorkOrders as a, TVehicles as v, TClients as c " +
		"where a.vehicle_id = v.id " +
		"	and v.client_id = c.id " +
		"	and status <> 'INVOICED'" +
		"	and dni like ?";

	@Override
	public void execute() throws BusinessException {
		String dni = Console.readString("Client DNI ");
		
		Console.println("\nClient's not invoiced work orders\n");  

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				Console.printf("\t%s \t%-40.40s \t%s \t%-12.12s \t%.2f\n",  
					rs.getString(1)
					, rs.getString(2) 
					, rs.getDate(3)
					, rs.getString(4)
					, rs.getDouble(5)
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}

}