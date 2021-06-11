package uo.ri.cws.application.persistence.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import alb.util.random.Random;
//import uo.ri.cws.application.persistence.intervention.InterventionRecord;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.mechanic.MechanicRecord;
import uo.ri.cws.application.persistence.order.OrderRecord;
import uo.ri.cws.application.persistence.order.OrderToGenerateRecord;
import uo.ri.cws.application.persistence.orderLines.OrderLinesRecord;
import uo.ri.cws.application.persistence.provider.ProviderRecord;
import uo.ri.cws.application.persistence.sparePart.SparePartRecord;
import uo.ri.cws.application.persistence.sparePart.SparePartReportRecord;
import uo.ri.cws.application.persistence.substitution.SubstitutionRecord;
import uo.ri.cws.application.persistence.supply.SupplyRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class RecordAssembler {

	//Para Mechanic
	/**
	 * Transforms a ResultSet into a MechanicRecord
	 */
	public static MechanicRecord toMechanicRecord(ResultSet m) throws SQLException {
		MechanicRecord dto = new MechanicRecord();
		dto.id = m.getString("id");
		dto.dni = m.getString("dni");
		dto.name = m.getString("name");
		dto.surname = m.getString("surname");
		return dto;
	}

	/**
	 * Transforms a ResultSet into a list of MechanicRecord
	 */
	public static List<MechanicRecord> toMechanicRecordList(ResultSet rs) throws SQLException {
		List<MechanicRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( toMechanicRecord( rs ) );
		}

		return res;
	}

	//Para Invoice
	/**
	 * Transforms a ResultSet into a InvoiceRecord
	 */
	public static InvoiceRecord toInvoiceRecord(ResultSet rs) throws SQLException {
		InvoiceRecord result = new InvoiceRecord();
		
		result.id = rs.getString("id");
		result.version = rs.getLong("version");
		result.date = rs.getDate("date").toLocalDate();
		result.number = rs.getLong("number");
		result.status = rs.getString("status");
		result.vat = rs.getDouble("vat");
		
		return result;
	}

	//Para WorkOrder
	/**
	 * Transforms a ResultSet into a WorkOrderRecord
	 */
	public static WorkOrderRecord toWorkOrderRecord ( ResultSet rs ) throws SQLException {
		WorkOrderRecord result = new WorkOrderRecord();
		
		result.id = rs.getString("id");
		result.total = rs.getLong("amount");
		result.date = rs.getDate( "date");
		result.description = rs.getString( "description");
		result.status = rs.getString( "status");
		result.version = rs.getLong("version");
		result.vehicleId = rs.getString( "vehicle_Id");

		// might be null
		result.mechanicId = rs.getString( "mechanic_Id");
		result.invoiceId = rs.getString( "invoice_Id");

		return result;
	}

	public static WorkOrderRecord toWorkOrderRecordInvoice ( ResultSet rs ) throws SQLException {
		WorkOrderRecord result = new WorkOrderRecord();

		result.id = rs.getString("id");
		result.description = rs.getString( "description");
		result.date = rs.getDate( "date");
		result.total = rs.getLong("amount");
		result.status = rs.getString( "status");

		return result;
	}

	/**
	 * Transforms a ResultSet into a list of WorkOrderRecord
	 */
	public static List<WorkOrderRecord> toWorkOrderRecordList(ResultSet rs) throws SQLException {
		List<WorkOrderRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( toWorkOrderRecord( rs ) );
		}
		return res;
	}

	public static List<WorkOrderRecord> toWorkOrderInvoiceRecordList(ResultSet rs) throws SQLException {
		List<WorkOrderRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( toWorkOrderRecordInvoice( rs ) );
		}
		return res;
	}

	//Para SparePart
	/**
	 * Transforms a ResultSet into a SparePartRecord
	 */
	public static SparePartRecord toSparePartRecord(ResultSet m) throws SQLException {
		SparePartRecord dto = new SparePartRecord();
		dto.id = m.getString("id");
		dto.version = m.getLong("version");
		dto.code = m.getString("code");
		dto.description = m.getString("description");
		dto.stock = m.getInt("stock");
		dto.minStock = m.getInt("minStock");
		dto.maxStock = m.getInt("maxStock");
		dto.price = m.getDouble("price");
		return dto;
	}

	/**
	 * Transforms a ResultSet into a SparePartReportRecord
	 */
	public static SparePartReportRecord toSparePartReportRecord(ResultSet m) throws SQLException {
		SparePartReportRecord dto = new SparePartReportRecord();
		dto.id = m.getString("id");
		dto.version = m.getLong("version");
		dto.code = m.getString("code");
		dto.description = m.getString("description");
		dto.stock = m.getInt("stock");
		dto.minStock = m.getInt("minStock");
		dto.maxStock = m.getInt("maxStock");
		dto.price = m.getDouble("price");
		dto.totalUnitsSold = m.getInt("totalUnitsSold");
		return dto;
	}

	/**
	 * Transforms a ResultSet into a SparePartReportRecord but with out the totalUnitsSold
	 */
	public static SparePartReportRecord toSparePartReportRecordWithOutUnitsSold(ResultSet m) throws SQLException {
		SparePartReportRecord dto = new SparePartReportRecord();
		dto.id = m.getString("id");
		dto.version = m.getLong("version");
		dto.code = m.getString("code");
		dto.description = m.getString("description");
		dto.stock = m.getInt("stock");
		dto.minStock = m.getInt("minStock");
		dto.maxStock = m.getInt("maxStock");
		dto.price = m.getDouble("price");
		return dto;
	}

	/**
	 * Transforms a ResultSet into a list of SparePartRecord
	 */
	public static List<SparePartRecord> toSparePartRecordList(ResultSet rs) throws SQLException {
		List<SparePartRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( toSparePartRecord( rs ) );
		}

		return res;
	}

	/**
	 * Transforms a ResultSet into a list of SparePartReportRecord
	 */
	public static List<SparePartReportRecord> toSparePartReportRecordList(ResultSet rs) throws SQLException {
		List<SparePartReportRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( toSparePartReportRecord( rs ) );
		}

		return res;
	}

	/**
	 * Transforms a ResultSet into a list of SparePartReportRecord but with out totalUnitsSold
	 */
	public static List<SparePartReportRecord> toSparePartReportRecordListWithOutSolds(ResultSet rs) throws SQLException {
		List<SparePartReportRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( toSparePartReportRecordWithOutUnitsSold( rs ) );
		}

		return res;
	}

	//Para Supplies
	/**
	 * Transforms a ResultSet into a SupplyRecord
	 */
	public static SupplyRecord toSupplyRecord(ResultSet m) throws SQLException {
		SupplyRecord dto = new SupplyRecord();
		dto.id = m.getString("id");
		dto.version = m.getLong("version");
		dto.providerId = m.getString("provider_Id");
		dto.sparePartId = m.getString("sparePart_Id");
		dto.deliveryTerm = m.getInt("deliveryTerm");
		dto.price = m.getDouble("price");
		return dto;
	}

	public static SupplyRecord toSupplyRecord(SupplyRecord supply) throws SQLException {
		SupplyRecord dto = new SupplyRecord();
		dto.providerId = supply.providerId;
		dto.price = supply.price;
		return dto;
	}

	/**
	 * Transforms a ResultSet into a SupplyRecord but only with providerId and price
	 */
	public static SupplyRecord toSupplyRecordP(ResultSet m) throws SQLException {
		SupplyRecord dto = new SupplyRecord();
		dto.providerId = m.getString("provider_id");
		dto.price = m.getDouble("price");
		return dto;
	}

	//Para Substitutions
	/**
	 * Transforms a ResultSet into a SubstitutionRecord
	 */
	public static SubstitutionRecord toSubstitutionRecord(ResultSet m) throws SQLException {
		SubstitutionRecord dto = new SubstitutionRecord();
		dto.id = m.getString("id");
		dto.version = m.getLong("version");
		dto.quantity = m.getInt("quantity");
		dto.sparePartId = m.getString("sparePart_Id");
		dto.interventionId = m.getString("intervention_Id");
		return dto;
	}

	//Para Providers
	/**
	 * Transforms a ResultSet into a ProviderRecord
	 */
	public static ProviderRecord toProviderRecord(ResultSet m) throws SQLException {
		ProviderRecord dto = new ProviderRecord();
		dto.id = m.getString("id");
		dto.version = m.getLong("version");
		dto.nif = m.getString("nif");
		dto.name = m.getString("name");
		dto.email = m.getString("email");
		dto.phone = m.getString("phone");
		return dto;
	}

	//Para Orders
	/**
	 * Transforms a ResultSet into a OrderRecord
	 */
	public static OrderRecord toOrderRecord(ResultSet m) throws SQLException {
		OrderRecord dto = new OrderRecord();
		dto.id = m.getString("id");
		dto.version = m.getLong("version");
		dto.code = m.getString("code");
		LocalDate ldate = m.getDate("orderedDate").toLocalDate();
		dto.orderedDate = ldate;
		if(m.getDate("receptionDate")!=null) {
			LocalDate ldate2 = m.getDate("receptionDate").toLocalDate();
			dto.receptionDate = ldate2;
		}
		dto.status = m.getString("status");
		dto.providerId = m.getString("provider_Id");
		dto.amount = m.getDouble("amount");
		return dto;
	}

	/**
	 * Transforms a ResultSet into a list of OrderRecord
	 */
	public static List<OrderRecord> toOrderRecordList(ResultSet rs) throws SQLException {
		List<OrderRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( toOrderRecord( rs ) );
		}

		return res;
	}

	//Para OrderLines
	/**
	 * Transforms a ResultSet into a list of OrderLinesRecord
	 */
	public static List<OrderLinesRecord> toOrderLinesRecordList(ResultSet rs) throws SQLException {
		List<OrderLinesRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( toOrderLinesRecord( rs ) );
		}

		return res;
	}

	/**
	 * Transforms a ResultSet into a OrderLinesRecord for the orderDetail
	 */
	public static OrderLinesRecord toOrderLinesRecord(ResultSet m) throws SQLException {
		OrderLinesRecord dto = new OrderLinesRecord();
		dto.price = m.getDouble("price");
		dto.quantity = m.getInt("quantity");
		
		dto.code = m.getString("code");
		dto.description = m.getString("description");
		return dto;
	}

	//Para generar Orders
	/**
	 * Transforms a ResultSet into OrderToGenerateRecord
	 */
	public static OrderToGenerateRecord toOrderToGenerateRecord(ResultSet m) throws SQLException {
		OrderToGenerateRecord dto = new OrderToGenerateRecord();
		dto.id = UUID.randomUUID().toString();
		//dto.nif = m.getString("nif");
		dto.sparepartId = m.getString("id");
		dto.spareCode = m.getString("code");
		dto.price = m.getDouble("price");
		dto.description = m.getString("description");
		dto.amount = m.getInt("quantity");
		dto.providerName = m.getString("name");
		LocalDate date = LocalDate.now();
		dto.orderedDate = date;
		dto.status = "PENDING";
		dto.code = Random.string(10);
		return dto;
	}

	/**
	 * Transforms a ResultSet into a list of OrderToGenerateRecord
	 */
	public static List<OrderToGenerateRecord> toOrderToGenerateRecordList(ResultSet rs) throws SQLException {
		List<OrderToGenerateRecord> res = new ArrayList<>();
		while(rs.next()) {
			
			res.add( toOrderToGenerateRecord( rs ) );
		}

		return res;
	}
}
