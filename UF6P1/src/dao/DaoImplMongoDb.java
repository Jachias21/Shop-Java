package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import model.Amount;
import model.Employee;
import model.Product;

public class DaoImplMongoDb implements dao {
	private MongoClient mongoClient;
	private MongoDatabase database;

	@Override
	public void connect() throws SQLException {
		// TODO Auto-generated method stub
		try {
			mongoClient = MongoClients.create("mongodb://localhost:27017");
			database = mongoClient.getDatabase("shop");
			System.out.println("Conexión exitosa");
		} catch (Exception e) {
			throw new SQLException("Error al conectar a MongoDb", e);
		}

	}

	@Override
	public Employee getEmployee(int employeeID, String password) {
		Employee employee = null;
		try {
			connect();
			MongoCollection<Document> collection = database.getCollection("users");
			Document query = new Document("employeeID", employeeID);
			Document userDoc = collection.find(query).first();
			if (userDoc != null) {
				String storedPassword = userDoc.getString("password");
				if (storedPassword.equals(password)) {
					employee = new Employee(userDoc.getString("name"));
					System.out.println("Empleado encontrado: " + employee.getName());
				} else {
					System.out.println("Contraseña incorrecta.");
				}
			} else {
				System.out.println("Empleado no encontrado.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}

	@Override
    public ArrayList<Product> getInventory() {
        ArrayList<Product> products = new ArrayList<>();
        MongoCollection<Document> inventoryCollection = database.getCollection("inventory");

        try {
            for (Document doc : inventoryCollection.find()) {
                int id = doc.getInteger("id");
                String name = doc.getString("name");

                Document wholesalerPriceDoc = doc.get("wholesalerprice", Document.class);
                double price = 0.0;
                if (wholesalerPriceDoc != null) {
                    Object priceObj = wholesalerPriceDoc.get("value");
                    if (priceObj instanceof Number) {
                        price = ((Number) priceObj).doubleValue();
                    }
                }

                boolean available = doc.getBoolean("available", true);
                int stock = doc.getInteger("stock", 0);

                Product product = new Product(name, available, stock, price);
                product.setId(id);
                product.setName(name);
                product.setStock(stock);
                product.setAvailable(available);
                product.setWholesalerPrice(new Amount(price, "€"));
                product.setPublicPrice(new Amount(price * 2, "€"));

                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


	@Override
	public boolean writeInventory(List<Product> inventory) {
	    try {
	        MongoCollection<Document> historicalInventory = database.getCollection("historical_inventory");
	        List<Document> documents = new ArrayList<>();

	        for (Product product : inventory) {
	            Document doc = new Document()
	                .append("id_product", product.getId())
	                .append("name", product.getName())
	                .append("price", product.getWholesalerPrice().getValue())
	                .append("stock", product.getStock())
	                .append("available", product.isAvailable())
	                .append("created_at", LocalDateTime.now().toString());

	            documents.add(doc);
	        }

	        if (!documents.isEmpty()) {
	            historicalInventory.insertMany(documents);
	            System.out.println("Inventario guardado en historical_inventory");
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    return false;
	}



	@Override
	public boolean addProduct(Product product) {
	    try {
	        MongoCollection<Document> inventoryCollection = database.getCollection("inventory");

	        if (product == null) {
	            throw new IllegalArgumentException("El producto no puede ser nulo");
	        }

	        Document wholesalePriceDoc = new Document("value", product.getWholesalerPrice().getValue())
	                                         .append("currency", product.getWholesalerPrice().getCurrency());

	        Document productDoc = new Document("id", product.getId())
	                                    .append("name", product.getName())
	                                    .append("stock", product.getStock())
	                                    .append("available", product.isAvailable())
	                                    .append("wholesaleprice", wholesalePriceDoc); 

	        inventoryCollection.insertOne(productDoc);
	        System.out.println("Producto añadido correctamente: " + product);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public boolean deleteProduct(Product product) {
	    if (product == null) {
	        throw new IllegalArgumentException("El producto no puede ser nulo");
	    }

	    try {
	        MongoCollection<Document> inventoryCollection = database.getCollection("inventory");

	        // Eliminamos el producto basado en su ID
	        DeleteResult result = inventoryCollection.deleteOne(Filters.eq("id", product.getId()));

	        if (result.getDeletedCount() > 0) {
	            System.out.println("Producto eliminado correctamente: " + product);
	            return true;
	        } else {
	            System.out.println("No se encontró el producto con ID: " + product.getId());
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public boolean updateProduct(Product product) {
	    if (product == null) {
	        throw new IllegalArgumentException("El producto no puede ser nulo");
	    }

	    try {
	        MongoCollection<Document> inventoryCollection = database.getCollection("inventory");

	        // Creamos el filtro para encontrar el producto por ID
	        Bson filter = Filters.eq("id", product.getId());

	        // Creamos la actualización con los nuevos valores
	        Bson update = Updates.combine(
	            Updates.set("name", product.getName()),
	            Updates.set("wholesaleprice.value", product.getWholesalerPrice().getValue()),
	            Updates.set("stock", product.getStock()),
	            Updates.set("available", product.isAvailable())
	        );

	        // Ejecutamos la actualización
	        UpdateResult result = inventoryCollection.updateOne(filter, update);

	        if (result.getModifiedCount() > 0) {
	            System.out.println("Producto actualizado correctamente: " + product);
	            return true;
	        } else {
	            System.out.println("No se encontró el producto con ID: " + product.getId());
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public void disconnect() throws SQLException {
		// TODO Auto-generated method stub
		try {
			if (mongoClient != null) {
				mongoClient.close();
				System.out.println("Desconectado de MongoDB");
			}
		} catch (Exception e) {
			throw new SQLException("Error al desconectar de MongoDB", e);
		}

	}

}
