package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Client;
public class Sale {
    Client client;
    ArrayList<Product> products;
    private Amount amount;
    LocalDateTime saleTime;
    
    

	public void setSaleTime(LocalDateTime saleTime) {
		this.saleTime = saleTime;
	}

	public Sale(Client client, ArrayList<Product> products, Amount amount, LocalDateTime saleTime) {
        this.client = client;
        this.products = products;
        this.amount = amount;
        this.saleTime = saleTime;
    }
	
    public LocalDateTime getSaleTime() {
		return saleTime;
	}
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
    
    public String getFormatteDateTime() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    	return saleTime.format(formatter);
    }
    
    public String toString() {
        StringBuilder boughtProducts = new StringBuilder();
        
        for (Product product : products) {
            if (product != null) {
                boughtProducts.append(product.getName()).append(", ");
            }
        }
        
        String productsString = boughtProducts.toString();
        
        return "Venta --> Client = " + client.name + " | Products = " + productsString + " | Amount = " + amount;
    }
}
