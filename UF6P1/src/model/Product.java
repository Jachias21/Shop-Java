package model;

import javax.persistence.*;

@Entity // Indica que esta clase es una entidad de JPA
@Table(name = "inventory") // Mapea esta clase con la tabla "inventory"
public class Product {
    
    @Id // Indica que este atributo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementar el ID
    @Column(name = "id") // Mapea con la columna "id"
    private int id;

    @Column(name = "name", nullable = false, length = 100) // Mapea con la columna "name"
    private String name;

    @Column(name = "price") // Mapea con la columna "price"
    private double price;

    @Transient // No mapea este atributo con ninguna columna
    private Amount publicPrice;

    @Transient // No mapea este atributo con ninguna columna
    private Amount wholesalerPrice;

    @Column(name = "available") // Mapea con la columna "available"
    private boolean available;

    @Column(name = "stock") // Mapea con la columna "stock"
    private int stock;

    @Transient // No mapea este atributo con ninguna columna
    private static int totalProducts;

    @Transient // No mapea este atributo con ninguna columna
    final static double EXPIRATION_RATE = 0.60;

    // Constructor con parámetros
    public Product(String name, Amount wholesalerPrice, boolean available, int stock) {
        this.name = name;
        this.wholesalerPrice = wholesalerPrice;
        this.publicPrice.setValue(getWholesalerPrice().getValue() * 2, null);
        this.available = available;
        this.stock = stock;
        totalProducts++;
    }
    
    public Product(String name, boolean available, int stock, double price) {
        this.name = name;
        this.price = price;
        this.wholesalerPrice = new Amount(price, "€");
        this.publicPrice = new Amount(price * 2, "€");
        this.available = available;
        this.stock = stock;
        totalProducts++;
    }

    // Constructor vacío
    public Product() {
        super();
    }

    // Getters y Setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        return "Product --> ID = " + id + " | Name = " + name + " | Price = " + price + " | Public Price = " + publicPrice
                + " | Whole Saler Price = " + wholesalerPrice + " | Available = " + available + " | Stock = " + stock;
    }
}
