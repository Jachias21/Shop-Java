package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Employee;
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
	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	@Override
	public List<Product> getInventory() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeInventory(List<Product> inventory) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
