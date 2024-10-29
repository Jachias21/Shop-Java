package model;

public class Product {
    private int id;
    private String name;
    private String origin;
    private Amount publicPrice;
    private Amount wholesalerPrice;
    private boolean available;
    private int stock;
    private static int totalProducts;
    private boolean deluxe;
    
    final static double EXPIRATION_RATE = 0.60;
    
    public Product(String name, Amount wholesalerPrice, boolean available, int stock) {
        super();
        this.id = totalProducts + 1;
        this.name = name;
        this.origin = origin;
        this.wholesalerPrice = wholesalerPrice;
        this.publicPrice = new Amount(0.0, ""); 
        this.publicPrice.setValue(getWholesalerPrice().getValue() * 2, null);
        this.available = available;
        this.stock = stock;
        totalProducts++;
        this.deluxe = deluxe;
    }

    public boolean isDeluxe() {
		return deluxe;
	}

	public void setDeluxe(boolean deluxe) {
		this.deluxe = deluxe;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getOrigin() {  
        return origin;
    }

    public void setOrigin(String origin) {  
        this.origin = origin;
    }

    public Amount getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Amount publicPrice) {
        this.publicPrice = publicPrice;
    }

    public Amount getWholesalerPrice() {
        return wholesalerPrice;
    }

    public void setWholesalerPrice(Amount wholesalerPrice) {
        this.wholesalerPrice = wholesalerPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public static int getTotalProducts() {
        return totalProducts;
    }

    public static void setTotalProducts(int totalProducts) {
        Product.totalProducts = totalProducts;
    }
    
    public void expire() {
        this.publicPrice.setValue(getPublicPrice().getValue() * EXPIRATION_RATE, null);
    }

    @Override
    public String toString() {
        return "Product --> Name = " + name + " | Origin = " + origin + " | Public Price = " + publicPrice + " | Whole Saler Price = " + wholesalerPrice
                + " | Available = " + available + " | Stock = " + stock + " | Deluxe = " + deluxe ;
    }
    
}
