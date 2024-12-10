package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Person;
import model.Product;

public class DaoImplJDBC implements dao {
	
	private Connection connection;
	
	public void connect() throws SQLException {
		// Define connection parameters
		String url = "jdbc:mysql://localhost:8889/Shop";
		String user = "root";
		String pass = "root";
		this.connection = DriverManager.getConnection(url, user, pass);
	}
	public static final String GET_INVENTORY = "SELECT * FROM historical_inventory";
	public static final String ADD_PRODUCT =  "INSERT INTO historical_inventory (id_product, name, wholesaler_price, available, stock, created_at) " + "VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
	public static final String DELETE_PRODUCT = "DELETE FROM historical_inventory WHERE id = ?";
	public static final String UPDATE_PRODUCT = "UPDATE historical_inventory SET id_product = ?, name = ?, wholesaler_price = ?, available = ?, stock = ? " + "WHERE id = ?";



	@Override
	public Employee getEmployee(int employeeID, String password) {
		Employee employee = null;
		// prepare query
		String query = "select * from employee where employeeID = ? ";
		
		try (PreparedStatement ps = connection.prepareStatement(query)) { 
			// set id to search for
			ps.setInt(1,employeeID);
		  	//System.out.println(ps.toString());
	        try (ResultSet rs = ps.executeQuery()) {
	        	if (rs.next()) {
	        		employee =  new Employee(rs.getString(1));            		            				
	        	}
	        }
	    } catch (SQLException e) {
			// in case error in SQL
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Product> getInventory() throws SQLException {
		ArrayList<Product> product = new ArrayList<>();
		try (Statement ps = connection.createStatement()) {

			try (ResultSet rs = ps.executeQuery(GET_INVENTORY)) {
				// for each result add to list
				while (rs.next()) {
					// get data for each person from column
					product.add(
							new Product(rs.getString(1), rs.getDouble(2), rs.getBoolean(3), rs.getInt(4)));
				}
			}
		}
		return product;
	}

	@Override
	public boolean writeInventory(List<Product> inventory) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}
