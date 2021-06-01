package uo.ri.cws.application.persistence.order;

import java.time.LocalDate;

public class OrderToGenerateRecord {
	public String id;

	public String sparepartId;
	public String spareCode;
	public double price;
	public String description;
	public int amount;
	public String providerName;
	public String providerId;
	public String nif;
	public LocalDate orderedDate;
	public LocalDate receptionDate;
	public String status;
	public String code;

}
