package uo.ri.cws.application.business.supply;

public class SupplyDto {
    public String id;
    public long version;

    public SupplierProviderDto provider = new SupplierProviderDto();
    public SuppliedSparePartDto sparePart = new SuppliedSparePartDto();

    public double price;
    public int deliveryTerm;

    public static class SupplierProviderDto {
	public String id;
	public String nif;
	public String name;
    }

    public static class SuppliedSparePartDto {
	public String id;
	public String code;
	public String description;
    }
}
