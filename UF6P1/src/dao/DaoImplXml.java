package dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import dao.xml.DomWriter;
import dao.xml.SaxReader;
import model.Employee;
import model.Product;

public class DaoImplXml implements dao {

	@Override
	public void connect() throws SQLException {

	}

	@Override
	public Employee getEmployee(int employeeID, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getInventory() throws IOException {
		List<Product> inventario = null;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			File file = new File(System.getProperty("user.dir") + File.separator + "Files/inputInventory.xml");
			SaxReader saxReader = new SaxReader();
			parser.parse(file, saxReader);
			inventario = saxReader.getProducts();
			System.out.println("Inventario cargado");

		} catch (ParserConfigurationException | SAXException e) {
			System.out.println("ERROR creating the parser");
		} catch (IOException e) {
			System.out.println("ERROR file not found");
		}
		return inventario;
	}

	@Override
	public boolean writeInventory(List<Product> inventory) throws SQLException {
		// TODO Auto-generated method stub
		DomWriter domWriter = new DomWriter();
		domWriter.generateDocument(inventory);
		return true;
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

}
