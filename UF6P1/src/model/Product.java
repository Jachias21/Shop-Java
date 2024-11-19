package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="product")
//@XmlType(propOrder= {"id","name","wholesalerPrice","available","publicPrice","stock"})
public class Product {
    private int id;
    private String name;
    private Amount publicPrice;
    private Amount wholesalerPrice;
    private boolean available;
    private int stock;
    private static int totalProducts;
    
    final static double EXPIRATION_RATE = 0.60;
    
    public Product(String name, Amount wholesalerPrice, boolean available, int stock) {
        super();
        this.id = totalProducts + 1;
        this.name = name;
        this.wholesalerPrice = wholesalerPrice;
        this.publicPrice = new Amount(0.0, ""); 
        this.publicPrice.setValue(getWholesalerPrice().getValue() * 2, null);
        this.available = available;
        this.stock = stock;
        totalProducts++;
    }
    
	public Product() {
		super();
	}


	//@XmlAttribute
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    //@XmlElement
    public Amount getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Amount publicPrice) {
        this.publicPrice = publicPrice;
    }
    
   @XmlElement
    public Amount getWholesalerPrice() {
        return wholesalerPrice;
    }

    public void setWholesalerPrice(Amount wholesalerPrice) {
        this.wholesalerPrice = wholesalerPrice;
    }
    
   @XmlAttribute
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
 //   @XmlAttribute
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
 //   @XmlAttribute
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
        return "Product --> Name = " + name + " | Public Price = " + publicPrice + " | Whole Saler Price = " + wholesalerPrice
                + " | Available = " + available + " | Stock = " + stock;
    }
    
}
