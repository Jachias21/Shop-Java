package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Employee;


public interface dao {
	
	void connect() throws SQLException;
	
	Employee getEmployee(int employeeID, String password);
	
	void disconnect() throws SQLException;
	
}
