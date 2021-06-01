package uo.ri.cws.application.persistence.orderLines;

public class OrderLinesRecord {
	public double price;
	public int quantity;
	public String sparepart_id;
	public String order_id;
	
	//Sparepart
	public String code;
	public String description;
	public int maxStock;
	public int minStock;
	public int stock;
	public double partPrice;
	public long version;

}
