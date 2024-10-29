package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Amount;
import model.Employee;
import model.Product;
import model.Sale;

public class DaoImplFile implements dao {

	@Override
	public void connect() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployee(int employeeID, String password) {
		
		return null;
	}
	public List<Product> getInventory() throws SQLException, IOException {
		ArrayList<Product> inventory2 = new ArrayList<>(); 
		   //find the file (where and what's is called)
		   File f = new File(System.getProperty("user.dir")+File.separator+"Files/inputInventory.txt");
		   //read the file, line by line
		   FileReader fr = new FileReader(f);
		   BufferedReader br = new BufferedReader(fr);
		   
		   String miLinea = br.readLine();
		   
		   while(miLinea != null) {
			   String[] partes1 = miLinea.split(";");
			   String product="";
			   String origin="";
			   Double wholesalerPrice = 0.00;
			   Amount amount = new Amount(0.00, "€"); 
			   int stock = 0;
			   boolean available = true;
			   boolean deluxe = false;
			   
			   for(int i = 0; i < partes1.length; i++) {
				   String[] partes2 = partes1[i].split(":");
				   switch (i) {
				   case 0: 
					   product = partes2[1];
					   break;
				   case 1:
					   origin = partes2[1];
					   break;
				   case 2:
					   wholesalerPrice = Double.parseDouble(partes2[1]);
					   amount = new Amount(wholesalerPrice, "€");
					   break;
				   case 3:
					   available = Boolean.parseBoolean(partes2[1]);
					   break;
				   case 4:
					   stock = Integer.parseInt(partes2[1]);
					   break;
				   case 5:
					   deluxe = Boolean.parseBoolean(partes2[1]);
					   break;
				   default:
					   break;
				   }
				   
			   }
			   
			   Product outproduct = new Product(product, amount, available, stock);
			   inventory2.add(outproduct);
			   miLinea = br.readLine();
		   }
		   fr.close();
		   br.close();
		
		return inventory2;
	}
	
	public boolean writeInventory(List<Product> inventory) throws SQLException {
	    // File path
	    File newFolder = new File(System.getProperty("user.dir") + File.separator + "files");
	    if (!newFolder.exists()) {
	        newFolder.mkdir();  // Create the folder if it doesn't exist
	    }

	    // Set the current date and format it
	    LocalDate currentDate = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String formattedDate = currentDate.format(formatter);

	    // File name
	    File inventoryFile = new File(newFolder, "inventory_" + formattedDate + ".txt");

	    // Check if the file does not exist, and create it if necessary
	    if (!inventoryFile.exists()) {
	        try {
	            inventoryFile.createNewFile();  // Create file if it doesn't exist
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "No se ha podido exportar", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    FileWriter fw = null;
	    PrintWriter pw = null;
	    try {
	        // Create FileWriter and PrintWriter objects
	        fw = new FileWriter(inventoryFile);
	        pw = new PrintWriter(fw);

	        int index = 1;

	        // Write the products and stock to the file
	        for (Product product : inventory) {
	            String productLine = index + ";Product:" + product.getName() + ";Stock:" + product.getStock() + ";";
	            pw.write(productLine);
	            pw.write("\n");
	            index++;
	        }

	        // Write the total number of products
	        pw.write("Numero total de productos:" + inventory.size() + ";");
	        pw.write("\n");

	        // Confirmation message
	        JOptionPane.showMessageDialog(null, "El inventario ha sido exportado", "Info", JOptionPane.INFORMATION_MESSAGE);
	        return true;

	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "No se ha podido exportar", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;

	    } finally {
	        // Ensure resources are closed in the finally block
	        try {
	            if (pw != null) pw.close();
	            if (fw != null) fw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


		

	
	public void disconnect() throws SQLException {
		// TODO Auto-generated method stub
		
	}


}
