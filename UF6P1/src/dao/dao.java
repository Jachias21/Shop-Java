package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Employee;
import model.Product;

import java.util.List;

public interface dao {

	void connect() throws SQLException;
	
	Employee getEmployee(int employeeID, String password);

	List<Product> getInventory() throws SQLException, FileNotFoundException, IOException;

	boolean writeInventory(List<Product> inventory) throws SQLException;

	boolean addProduct(Product product) throws SQLException;

	boolean deleteProduct(String name) throws SQLException;

	boolean updateProduct(Product product) throws SQLException;

	void disconnect() throws SQLException;

}
