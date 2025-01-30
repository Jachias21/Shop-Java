package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.dao;
import dao.jaxb.JaxbMarshaller;
import dao.jaxb.JaxbUnMarshaller;
import model.Employee;
import model.Product;
import model.ProductHistory;

public class DaoImplJaxb implements dao {

	@Override
	public void connect() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployee(int employeeID, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getInventory() throws SQLException, FileNotFoundException, IOException {
	    List<Product> inventario = null;

	    try {
	        JaxbUnMarshaller unmarshaller = new JaxbUnMarshaller();
	        ProductHistory productList = unmarshaller.unmarshalProducts("jaxb/inputInventory.xml");

	        if (productList != null) {
	            inventario = productList.getProducts();
	        } else {
	            System.out.println("Error unmarshalling products.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error during XML unmarshalling");
	        e.printStackTrace();
	    }

	    return inventario;
	}



	@Override
	public boolean writeInventory(List<Product> inventory) throws SQLException {
	    try {
	        ProductHistory productList = new ProductHistory(new ArrayList<>(inventory));
	        JaxbMarshaller marshaller = new JaxbMarshaller();
	        marshaller.init(productList);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error writing inventory to XML.");
	        return false;
	    }
	}


	@Override
	public void disconnect() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(String name) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
