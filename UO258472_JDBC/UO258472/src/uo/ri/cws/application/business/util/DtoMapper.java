package uo.ri.cws.application.business.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.order.OrderDto;
import uo.ri.cws.application.business.order.OrderDto.OrderLineDto;
import uo.ri.cws.application.business.order.OrderDto.OrderedSpareDto;
import uo.ri.cws.application.business.order.OrderDto.OrderedProviderDto;
import uo.ri.cws.application.business.order.OrderLinesDto;
import uo.ri.cws.application.business.provider.ProviderDto;
import uo.ri.cws.application.business.sparePart.SparePartDto;
import uo.ri.cws.application.business.sparePart.SparePartReportDto;
import uo.ri.cws.application.business.supply.SupplyDto;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.mechanic.MechanicRecord;
import uo.ri.cws.application.persistence.order.OrderRecord;
import uo.ri.cws.application.persistence.order.OrderToGenerateRecord;
import uo.ri.cws.application.persistence.orderLines.OrderLinesRecord;
import uo.ri.cws.application.persistence.provider.ProviderRecord;
import uo.ri.cws.application.persistence.sparePart.SparePartRecord;
import uo.ri.cws.application.persistence.sparePart.SparePartReportRecord;
import uo.ri.cws.application.persistence.supply.SupplyRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

import javax.swing.text.html.Option;

public class DtoMapper {

	//Mechanics
	public static MechanicDto toDto(String id, String dni, String name, String surname) {
		MechanicDto result = new MechanicDto();
		result.id = id;
		result.name = name;
		result.surname = surname;
		result.dni = dni;
		return result;
	}

	/**
	 *Transforms a MechanicRecord into a MechanicDto
	 */
	public static Optional<MechanicDto> toDto(Optional<MechanicRecord> arg) {
		Optional<MechanicDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDto(arg.get().id, arg.get().dni, arg.get().name, arg.get().surname));
		return result;
	}

	/**
	 *Transforms a list of MechanicRecord into a list of MechanicDto
	 */
	public static List<MechanicDto> toDtoList(List<MechanicRecord> arg) {
		List<MechanicDto> result = new ArrayList<MechanicDto> ();
		for (MechanicRecord mr : arg) 
			result.add(toDto(mr.id, mr.dni, mr.name, mr.surname));
		return result;
	}

	public static MechanicRecord toRecord(MechanicDto arg) {
		MechanicRecord result = new MechanicRecord ();
		result.id = arg.id;
		result.dni = arg.dni;
		result.name = arg.name;
		result.surname = arg.surname;
		return result;
	}

	//Invoices
	public static InvoiceDto toDto(InvoiceRecord arg) {
		InvoiceDto result = new InvoiceDto();
		result.id = arg.id;
		result.number = arg.number;
		result.status = arg.status;
		result.date = arg.date;
		result.total = arg.total;
		result.vat = arg.vat;
		return result;
	}
	
	//SpareParts
	public static SparePartDto toDto(String id, String code, String description, int minStock, int maxStock, double price, int stock, long version) {
		SparePartDto result = new SparePartDto();
		result.id = id;
		result.code = code;
		result.description = description;
		result.maxStock = maxStock;
		result.minStock = minStock;
		result.price = price;
		result.stock = stock;
		result.version = version;
		return result;
	}
	
	public static SparePartRecord toRecord(SparePartDto arg) {
		SparePartRecord result = new SparePartRecord ();
		result.id = arg.id;
		result.version=arg.version;
		result.code = arg.code;
		result.description = arg.description;
		result.stock = arg.stock;
		result.minStock=arg.minStock;
		result.maxStock = arg.maxStock;
		result.price = arg.price;
		return result;
	}
	
	public static SparePartDto toDto(SparePartRecord arg) {
		SparePartDto result = new SparePartDto();
		result.id = arg.id;
		result.code = arg.code;
		result.description = arg.description;
		result.maxStock = arg.maxStock;
		result.minStock = arg.minStock;
		result.price = arg.price;
		result.stock = arg.stock;
		result.version = arg.version;
		return result;
	}

	/**
	 *Transforms a list of SparePartRecord into a list of SparePartDto
	 */
	public static List<SparePartDto> toDtoListSP(List<SparePartRecord> arg) {
		List<SparePartDto> result = new ArrayList<SparePartDto> ();
		for (SparePartRecord spr : arg) 
			result.add(toDto(spr.id, spr.code, spr.description, spr.maxStock, spr.minStock, spr.price, spr.stock, spr.version));
		return result;
	}

	/**
	 *Transforms a SparepartRecord into SparePartDto
	 */
	public static Optional<SparePartDto> toDtoSP(Optional<SparePartRecord> arg) {
		Optional<SparePartDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDto(arg.get().id, arg.get().code, arg.get().description, arg.get().maxStock, arg.get().minStock, arg.get().price, arg.get().stock, arg.get().version));
		return result;
	}

	//SparePartReport
	public static SparePartReportDto toDto(SparePartReportRecord arg) {
		SparePartReportDto result = new SparePartReportDto();
		result.id = arg.id;
		result.code = arg.code;
		result.description = arg.description;
		result.maxStock = arg.maxStock;
		result.minStock = arg.minStock;
		result.price = arg.price;
		result.stock = arg.stock;
		result.version = arg.version;
		result.totalUnitsSold = arg.totalUnitsSold;
		return result;
	}

	/**
	 *Transforms a SparePartReportRecord into SparePartReportDto
	 */
	public static Optional<SparePartReportDto> toDtoSPRep(Optional<SparePartReportRecord> arg) {
		Optional<SparePartReportDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDto(arg.get().id, arg.get().code, arg.get().description, arg.get().maxStock, arg.get().minStock, arg.get().price, arg.get().stock, arg.get().version, arg.get().totalUnitsSold));
		return result;
	}

	/**
	 *Transforms a list of SparePartReportRecord into a list of SparePartReportDto
	 */
	public static List<SparePartReportDto> toDtoListSPRep(List<SparePartReportRecord> arg) {
		List<SparePartReportDto> result = new ArrayList<SparePartReportDto> ();
		for (SparePartReportRecord spr : arg)
			result.add(toDto(spr.id, spr.code, spr.description, spr.maxStock, spr.minStock, spr.price, spr.stock, spr.version, spr.totalUnitsSold));
		return result;
	}

	public static SparePartReportDto toDto(String id, String code, String description, int minStock, int maxStock, double price, int stock, long version, int totalUnitsSold) {
		SparePartReportDto result = new SparePartReportDto();
		result.id = id;
		result.code = code;
		result.description = description;
		result.maxStock = maxStock;
		result.minStock = minStock;
		result.price = price;
		result.stock = stock;
		result.version = version;
		result.totalUnitsSold = totalUnitsSold;
		return result;
	}

	//Orders
	public static OrderDto toDto(OrderRecord arg) {
		OrderDto result = new OrderDto();
		result.id = arg.id;
		result.version = arg.version;
		result.code = arg.code;
		result.orderedDate = arg.orderedDate;
		result.receptionDate = arg.receptionDate;
		result.amount = arg.amount;
		result.status = arg.status;
		result.provider.id = arg.providerId;
		return result;
	}

	public static OrderToGenerateRecord toRecordOrIns(OrderDto arg) {
		OrderToGenerateRecord result = new OrderToGenerateRecord ();
		result.id = UUID.randomUUID().toString();
		result.code = arg.code;
		result.price = arg.amount;
		result.orderedDate = arg.orderedDate;
		result.status = arg.status;
		result.providerId = arg.provider.id;
		return result;
	}
	
	public static OrderDto toDto(String id, long version, String code, LocalDate orderedDate, LocalDate receptionDate, double amount, String status, String providerId) {
		OrderDto result = new OrderDto();
		result.id = id;
		result.version = version;
		result.code = code;
		result.orderedDate = orderedDate;
		result.receptionDate = receptionDate;
		result.amount = amount;
		result.status = status;
		result.provider.nif = providerId;
		return result;
	}

	/**
	 *Transforms a list of OrderRecord into a list of OrderDto
	 */
	public static List<OrderDto> toDtoListOrd(List<OrderRecord> arg) {
		List<OrderDto> result = new ArrayList<OrderDto> ();
		for (OrderRecord or : arg) 
			result.add(toDto(or.id, or.version, or.code, or.orderedDate, or.receptionDate, or.amount, or.status, or.providerId));
		return result;
	}

	/**
	 *Transforms a OrderRecord into OrderDto
	 */
	public static Optional<OrderDto> toDtoOr(Optional<OrderRecord> arg) {
		Optional<OrderDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDto(arg.get().id, arg.get().version, arg.get().code, arg.get().orderedDate, arg.get().receptionDate, arg.get().amount, arg.get().status, arg.get().providerId));
		return result;
	}
	
	//GenerateOrders
	/**
	 *Transforms a list of OrderToGenerateRecord into a list of OrderDto
	 */
	public static List<OrderDto> toDtoListOrdToGenerate(List<OrderToGenerateRecord> arg) {
		List<OrderDto> result = new ArrayList<OrderDto> ();
		for (OrderToGenerateRecord or : arg) 
			result.add(toDto(or.id, or.sparepartId, or.spareCode, or.price, or.description, or.amount, or.providerName, or.nif, or.orderedDate, or.receptionDate, or.status, or.code));
		return result;
	}
	
	public static OrderDto toDto(String id, String sparepartId, String spareCode, double price, String description, int amount, String providerName, String nif, LocalDate orderedDate, LocalDate receptionDate, String status, String code) {
		OrderDto result = new OrderDto();
		OrderLineDto lines = new OrderLineDto();
		result.id = id;
		result.code = code;
		result.orderedDate = orderedDate;
		result.amount = amount;
		result.receptionDate = receptionDate;
		result.status = status;
		result.provider.name = providerName;
		result.provider.nif = nif;
		lines.price = price;
		lines.quantity = amount;
		lines.sparePart.id = sparepartId;
		lines.sparePart.code = spareCode;
		lines.sparePart.description = description;
		return result;
	}

	//OrderLines
	public static OrderLineDto toDto(int quantity, double price, String description, String code, String sparepart_id) {
		OrderLineDto result = new OrderLineDto();
		result.quantity = quantity;
		result.price = price;

		result.sparePart.description = description;
		result.sparePart.code = code;
		result.sparePart.id = sparepart_id;
		return result;
	}

	/**
	 *Transforms a list of OrderLinesRecord into a list of OrderLineDto
	 */
	public static List<OrderLineDto> toDtoListOrdLin(List<OrderLinesRecord> arg) {
		List<OrderLineDto> result = new ArrayList<OrderLineDto> ();
		for (OrderLinesRecord or : arg) 
			result.add(toDto(or.quantity, or.price, or.description, or.code, or.sparepart_id));
		return result;
	}

	//Providers
	public static ProviderDto toDto(ProviderRecord arg) {
		ProviderDto result = new ProviderDto();
		result.id = arg.id;
		result.version = arg.version;
		result.nif = arg.nif;
		result.name = arg.name;
		result.email = arg.email;
		result.phone = arg.phone;
		return result;
	}
	
	public static ProviderDto toDto(String id, long version, String nif, String name, String email, String phone) {
		ProviderDto result = new ProviderDto();
		result.id = id;
		result.version = version;
		result.nif = nif;
		result.name = name;
		result.email = email;
		result.phone = phone;
		return result;
	}

	/**
	 *Transforms a ProviderRecord into ProviderDto
	 */
	public static Optional<ProviderDto> toDtoPro(Optional<ProviderRecord> arg) {
		Optional<ProviderDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDto(arg.get().id, arg.get().version, arg.get().nif, arg.get().name, arg.get().email, arg.get().phone));
		return result;
	}

	//Supplies
	/**
	 *Transforms a SupplyRecord into SupplyDto
	 */
	public static Optional<SupplyDto> toDtoSup(Optional<SupplyRecord> arg) {
		Optional<SupplyDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDto(arg.get().providerId, arg.get().price));
		return result;
	}

	public static SupplyDto toDto(SupplyRecord arg) {
		SupplyDto result = new SupplyDto();
		result.provider.id = arg.providerId;
		result.price = arg.price;
		return result;
	}

	public static SupplyDto toDto(String provider_id, double price) {
		SupplyDto result = new SupplyDto();
		result.provider.id = provider_id;
		result.price = price;
		return result;
	}

    public static OrderedSpareDto toOrderedDto(SparePartRecord p) {
		OrderedSpareDto osDto = new OrderedSpareDto();
		osDto.code = p.code;
		osDto.id = p.id;
		osDto.description = p.description;
		return osDto;
    }

	public static OrderedProviderDto toDtoForOrderedProvider(ProviderRecord provider) {
		OrderedProviderDto result = new OrderedProviderDto();
		result.id = provider.id;
		result.nif = provider.nif;
		result.name = provider.name;
		return result;
	}

	public static OrderRecord toRecord(OrderDto dto) {
		OrderRecord result = new OrderRecord();
		result.code = dto.code;
		result.id = dto.id;
		result.orderedDate = dto.orderedDate;
		result.amount = dto.amount;
		result.providerId = dto.provider.id;
		result.status = dto.status;
		result.version = dto.version;
		return result;
	}

	public static OrderLinesRecord toRecord(OrderLineDto orderLineDto, String orderId) {
		OrderLinesRecord result = new OrderLinesRecord();
		result.order_id = orderId;
		result.sparepart_id = orderLineDto.sparePart.id;
		result.price = orderLineDto.price;
		result.quantity = orderLineDto.quantity;

		return result;
	}

    public static ProviderRecord toRecord(ProviderDto provider) {
		ProviderRecord result = new ProviderRecord();
		result.id = provider.id;
		result.nif = provider.nif;
		result.name = provider.name;
		result.email = provider.email;
		result.phone = provider.phone;

		return result;
    }

    public static List<ProviderDto> toDtoListProv(List<ProviderRecord> arg) {
		List<ProviderDto> result = new ArrayList<>();
		for (ProviderRecord pr : arg)
			result.add(toDto(pr.id, pr.version, pr.nif, pr.name, pr.email, pr.phone));
		return result;
    }

    public static SupplyRecord toRecord(SupplyDto supply) {
		SupplyRecord result = new SupplyRecord();
		result.id = supply.id;
		result.deliveryTerm = supply.deliveryTerm;
		result.price = supply.price;
		result.providerId = supply.provider.id;
		result.sparePartId = supply.sparePart.id;
		result.version = supply.version;

		return result;
    }

	public static Optional<SupplyDto> toDtoCompleteSupply(Optional<SupplyRecord> arg) {
		Optional<SupplyDto> result = arg.isEmpty()?Optional.ofNullable(null)
				:Optional.ofNullable(toDtoSupply(arg.get().id, arg.get().deliveryTerm, arg.get().price, arg.get().version, arg.get().providerId, arg.get().sparePartId));
		return result;
	}

	public static SupplyDto toDtoSupply(String id, int deliveryTerm, double price, Long version, String providerId, String sparePartId) {
		SupplyDto result = new SupplyDto();
		result.provider.id = providerId;
		result.sparePart.id = sparePartId;
		result.deliveryTerm = deliveryTerm;
		result.id = id;
		result.version = version;
		result.price = price;
		return result;
	}


    public static List<SupplyDto> toDto(List<SupplyRecord> arg) {
		List<SupplyDto> result = new ArrayList<>();
		for (SupplyRecord sr : arg) {
			result.add(toDtoSupply(sr.id, sr.deliveryTerm, sr.price, sr.version, sr.providerId, sr.sparePartId));
		}
		return result;
    }
}