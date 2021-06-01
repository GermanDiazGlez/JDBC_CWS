package uo.ri.cws.application.persistence.workorder;

import java.util.Date;

public class WorkOrderRecord {
	
	public String id;
	public Long version;

	public String vehicleId;
	public String description;
	public Date date;
	public double total;
	public String status;

	// might be null
	public String mechanicId;
	public String invoiceId;

}
