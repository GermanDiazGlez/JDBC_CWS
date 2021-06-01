package uo.ri.cws.application.business.order;

public class OrderLinesDto {
	public OrderedSpareDto sparePart = new OrderedSpareDto();
	public double price;
	public int quantity;
	
	
    public static class OrderedSpareDto {
	public String id;
	public String code;
	public String description;
    }

}
