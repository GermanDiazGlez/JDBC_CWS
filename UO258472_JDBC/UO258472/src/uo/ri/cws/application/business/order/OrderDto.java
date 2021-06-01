package uo.ri.cws.application.business.order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    public String id;
    public long version;

    public String code;
    public LocalDate orderedDate;
    public LocalDate receptionDate;
    public double amount;
    public String status;

    public OrderedProviderDto provider = new OrderedProviderDto();
    public List<OrderLineDto> lines = new ArrayList<>();
    
    

    public static class OrderLineDto {
	public OrderedSpareDto sparePart = new OrderedSpareDto();
	public double price;
	public int quantity;
    }

    public static class OrderedSpareDto {
	public String id;
	public String code;
	public String description;
    }

    public static class OrderedProviderDto {
	public String id;
	public String nif;
	public String name;
    }

}
