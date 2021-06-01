package uo.ri.cws.application.persistence.order;

import java.time.LocalDate;

public class OrderRecord {
	public String id;
	public long version;

	public String code;
	public LocalDate orderedDate;
	public LocalDate receptionDate;
	public double amount;
	public String status;

	public String providerId;

}
