package uo.ri.cws.application.ui.util;

import java.util.List;

import alb.util.console.Console;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.PaymentMeanDto;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrderDto.OrderLineDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.sparePart.SparePartReportDto;
import uo.ri.cws.application.business.supply.SupplyDto;

public class Printer {

	public static void printInvoice(InvoiceDto invoice) {

		double importeConIVa = invoice.total;
		double iva =  invoice.vat;
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Invoice #: %d\n", 				invoice.number );
		Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", 	invoice.date);
		Console.printf("\tTotal: %.2f €\n", 			importeSinIva);
		Console.printf("\tTax: %.1f %% \n", 			invoice.vat );
		Console.printf("\tTotal, tax inc.: %.2f €\n", 	invoice.total );
		Console.printf("\tStatus: %s\n", 				invoice.status );
	}

	public static void printPaymentMeans(List<PaymentMeanDto> medios) {
		Console.println();
		Console.println("Available payment means");

		Console.printf("\t%s \t%-8.8s \t%s \n", "Id", "Type", "Acummulated");
		for (PaymentMeanDto medio : medios) {
			printPaymentMean( medio );
		}
	}

	private static void printPaymentMean(PaymentMeanDto medio) {
		Console.printf("\t%s \t%-8.8s \t%s \n"
				, medio.id
				, medio.getClass().getName()  // not the best...
				, medio.accumulated
		);
	}

	public static void printMechanic(MechanicDto m) {

		Console.printf("\t%s %-10.10s %-15.15s %-25.25s\n"
				, m.id
				, m.dni
				, m.name
				, m.surname
			);
	}

	public static void printMechanics(List<MechanicDto> list) {

		Console.printf("\t%-36s %-10.10s %-25.25s %-25.25s%n",  
				"Mechanic identifier"
				, "DNI"
				, "Name"
				, "Surname"
				);
		for (MechanicDto m : list )
			printMechanic(m);
	}
	
	public static void printSummary(OrderDto o) {
		Console.printf("\t%s %-10.10s %td/%<tm/%<tY %-8.8s %9.2f € %s\n"
				, o.code
				, o.provider.nif
				, o.orderedDate
				, o.status
				, o.amount
				, o.provider.name
			);
	}

	public static void printDetail(OrderDto o) {
		printSummary( o );
		Console.printf("\t%-10.10s %td/%<tm/%<tY %td/%<tm/%<tY %6.2f € %s\n"
				, o.provider.name
				, o.orderedDate
				, o.receptionDate
				, o.amount
				, o.status
			);
		for(OrderLineDto l : o.lines) {
			Console.printf("\t\t%-10.10s %-20.20s %3d %6.2f €\n"
				, l.sparePart.code
				, l.sparePart.description
				, l.quantity
				, l.price
			);
		}
	}

	public static void print(ProviderDto p) {
		Console.printf("\t%-10.10s %-20.20s %-20.20s %-11.11s\n"
				, p.nif
				, p.name
				, p.email
				, p.phone
			);
	}

	public static void print(SparePartDto sp) {
		Console.printf("\t%-10.10s %-30.30s %4d %4d %4d %6.2f €\n"
				, sp.code
				, sp.description
				, sp.stock
				, sp.minStock
				, sp.maxStock
				, sp.price
			);
	}

	public static void print(SparePartReportDto sp) {
		Console.printf("\t%-10.10s %-30.30s %4d %4d %4d %6.2f € %5d\n"
				, sp.code
				, sp.description
				, sp.stock
				, sp.minStock
				, sp.maxStock
				, sp.price
				, sp.totalUnitsSold
			);
	}

	public static void print(SupplyDto s) {
		Console.printf("\t%-10.10s %-20.20s %-10.10s %-30.30s %6.2f € %2d\n"
				, s.provider.nif
				, s.provider.name
				, s.sparePart.code
				, s.sparePart.description
				, s.price
				, s.deliveryTerm
			);
	}
	

}
