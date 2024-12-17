package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Amount;
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

	public static final String GET_INVENTORY = "SELECT * FROM inventory";
	public static final String ADD_PRODUCT = "INSERT INTO inventory (id, name, wholesaler_price, available, stock) "
			+ "VALUES (?, ?, ?, ?, ?)";
	public static final String DELETE_PRODUCT = "DELETE FROM inventory WHERE name = ?";
	public static final String UPDATE_PRODUCT = "UPDATE inventory SET stock = ?" + " WHERE name = ?";
	public static final String WRITE_INVENTORY = "INSERT INTO historical_inventory (id_product, name, wholesaler_price, available, stock, created_at)";

	@Override
	public Employee getEmployee(int employeeID, String password) {
		Employee employee = null;
		// prepare query
		String query = "select * from employee where employeeID = ? ";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
			// set id to search for
			ps.setInt(1, employeeID);
			// System.out.println(ps.toString());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					employee = new Employee(rs.getString(1));
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
		Amount wholesalerPrice = new Amount();
		try (Statement ps = connection.createStatement()) {

			try (ResultSet rs = ps.executeQuery(GET_INVENTORY)) {
				while (rs.next()) {
					wholesalerPrice = new Amount(rs.getDouble(3), "€");
					product.add(new Product(rs.getInt(1), rs.getString(2), wholesalerPrice, rs.getBoolean(4),
							rs.getInt(5)));
					System.out.println("inventario cargado");
				}
			}
		}
		return product;
	}

	@Override
	public boolean writeInventory(List<Product> inventory) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(WRITE_INVENTORY + " VALUES (?, ?, ?, ?, ?, ?)")) {
            for (Product product : inventory) {
                preparedStatement.setInt(1, product.getId()); 
                preparedStatement.setString(2, product.getName()); 
                preparedStatement.setDouble(3, product.getWholesalerPrice().getValue());
                preparedStatement.setBoolean(4, product.isAvailable());
                preparedStatement.setInt(5, product.getStock()); 
                preparedStatement.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
                preparedStatement.executeUpdate();
            } 
		return true;
		}
	}
	@Override
	public boolean addProduct(Product product) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT)) {
			preparedStatement.setInt(1, product.getId());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setDouble(3, product.getWholesalerPrice().getValue());
			preparedStatement.setBoolean(4, product.isAvailable());
			preparedStatement.setInt(5, product.getStock());
			preparedStatement.executeUpdate();
		}

		return true;
	}

	@Override
	public boolean deleteProduct(String name) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
		}
		return true;
	}

	@Override
	public boolean updateProduct(Product product) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
			preparedStatement.setInt(1, product.getStock());
			preparedStatement.setString(2, product.getName());
			preparedStatement.executeUpdate();
		}
		return true;
	}

	@Override
	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}
