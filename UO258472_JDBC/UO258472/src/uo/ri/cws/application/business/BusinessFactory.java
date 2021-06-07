package uo.ri.cws.application.business;

import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.create.InvoicingServiceImpl;
//import uo.ri.cws.application.business.invoice.CreateInvoiceService;
//import uo.ri.cws.application.business.invoice.create.CreateInvoiceServiceImpl;
import uo.ri.cws.application.business.mechanic.MechanicCrudService;
import uo.ri.cws.application.business.mechanic.crud.MechanicCrudServiceImpl;
import uo.ri.cws.application.business.order.OrdersService;
import uo.ri.cws.application.business.order.impl.OrdersServiceImpl;
import uo.ri.cws.application.business.provider.ProvidersCrudService;
import uo.ri.cws.application.business.provider.impl.ProvidersCrudServiceImpl;
import uo.ri.cws.application.business.sparePart.SparePartCrudService;
import uo.ri.cws.application.business.sparePart.SparePartReportService;
import uo.ri.cws.application.business.sparePart.impl.SparePartCrudServiceImpl;
import uo.ri.cws.application.business.sparePart.impl.SparePartReportServiceImpl;
import uo.ri.cws.application.business.supply.SuppliesCrudService;
import uo.ri.cws.application.business.supply.impl.SuppliesCrudServiceImpl;

public class BusinessFactory {


	public static MechanicCrudService forMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}

	public static InvoicingService forCreateInvoiceService() {
		return new InvoicingServiceImpl();
	}

	public static ProvidersCrudService forProvidersService(){
		return new ProvidersCrudServiceImpl();
	}

	public static OrdersService forOrdersService(){
		return new OrdersServiceImpl();
	}

	public static SparePartReportService forSparePartReportService(){
		return new SparePartReportServiceImpl();
	}

	public static SuppliesCrudService forSuppliesCrudService(){
		return new SuppliesCrudServiceImpl();
	}

	public static SparePartCrudService forSparePartCrudService() {
		return new SparePartCrudServiceImpl();
	}


//	public static WorkOrderCrudService forWorkOrderService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CloseWorkOrderService forClosingBreakdown() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static VehicleCrudService forVehicleCrudService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CourseCrudService forCourseCrudService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CourseAttendanceService forCourseAttendanceService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CourseReportService forCourseReportService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static CertificateService forCertificateService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static VehicleTypeCrudService forVehicleTypeCrudService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static AssignWorkOrderService forAssignWorkOrderService() {
//		return new AssignWorkOrderServiceImpl();
//	}
//
//	public static ClientCrudService forClientCrudService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static SettleInvoiceService forSettleInvoiceService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static ClientHistoryService forClientHistoryService() {
//		throw new RuntimeException("Not yet implemented");
//	}
//
//	public static ViewAssignedWorkOrdersService forViewAssignedWorkOrdersService() {
//		throw new RuntimeException("Not yet implemented");
//	}
	

}

