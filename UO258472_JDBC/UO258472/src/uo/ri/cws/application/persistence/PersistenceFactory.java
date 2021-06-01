package uo.ri.cws.application.persistence;

import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.impl.InvoiceGatewayImpl;
//import uo.ri.cws.application.persistence.intervention.InterventionGateway;
//import uo.ri.cws.application.persistence.intervention.impl.InterventionGatewayImpl;
//import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
//import uo.ri.cws.application.persistence.invoice.impl.InvoiceGatewayImpl;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;
import uo.ri.cws.application.persistence.mechanic.impl.MechanicGatewayImpl;
import uo.ri.cws.application.persistence.order.OrderGateway;
import uo.ri.cws.application.persistence.order.impl.OrderGatewayImpl;
import uo.ri.cws.application.persistence.orderLines.OrderLinesGateway;
import uo.ri.cws.application.persistence.orderLines.impl.OrderLinesGatewayImpl;
import uo.ri.cws.application.persistence.provider.ProviderGateway;
import uo.ri.cws.application.persistence.provider.impl.ProviderGatewayImpl;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;
import uo.ri.cws.application.persistence.sparePart.SparePartReportGateway;
import uo.ri.cws.application.persistence.sparePart.impl.SparePartGatewayImpl;
import uo.ri.cws.application.persistence.sparePart.impl.SparePartReportGatewayImpl;
import uo.ri.cws.application.persistence.substitution.SubstitutionGateway;
import uo.ri.cws.application.persistence.substitution.impl.SubstitutionGatewayImpl;
//import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
//import uo.ri.cws.application.persistence.workorder.impl.WorkOrderGatewayImpl;
import uo.ri.cws.application.persistence.supply.SupplyGateway;
import uo.ri.cws.application.persistence.supply.impl.SupplyGatewayImpl;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.impl.WorkOrderGatewayImpl;

public class PersistenceFactory {

	public static MechanicGateway forMechanic() {
		return new MechanicGatewayImpl();
	}
	
	public static SparePartGateway forSparePart() {
		return new SparePartGatewayImpl();
	}

	public static SparePartReportGateway forSparePartReport() {
		return new SparePartReportGatewayImpl();
	}


		public static WorkOrderGateway forWorkOrder() {
		return new WorkOrderGatewayImpl();
	}

	public static InvoiceGateway forInvoice() {
		return new InvoiceGatewayImpl();
	}

//	public static InterventionGateway forIntervention() {
//		return new InterventionGatewayImpl();
//	}

	public static SupplyGateway forSupplies() {
		return new SupplyGatewayImpl();
	}
	
	public static SubstitutionGateway forSubstitutions() {
		return new SubstitutionGatewayImpl();
	}

	public static OrderGateway forOrders() {
		return new OrderGatewayImpl();	
	}
	
	public static OrderLinesGateway forOrderLines() {
		return new OrderLinesGatewayImpl();	
	}
	
	public static ProviderGateway forProviders() {
		return new ProviderGatewayImpl();
	}
}

