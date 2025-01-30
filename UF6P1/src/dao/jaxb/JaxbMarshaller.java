package dao.jaxb;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import model.ProductHistory;


public class JaxbMarshaller {
	
  
    public void init (ProductHistory inventory) {
		try {
			LocalDate currentDate = LocalDate.now();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	String formattedDate = currentDate.format(formatter);
	    	File file = new File("JaxB/inventory_" + formattedDate + ".xml");
			JAXBContext context = JAXBContext.newInstance(ProductHistory.class);
			Marshaller marshaller = context.createMarshaller();
			System.out.println("marshalling... ");
			ProductHistory products = inventory;
			marshaller.marshal(products, file);
			System.out.println("marshalled");
            JOptionPane.showMessageDialog(null, "Archivo exportado", "Exported", JOptionPane.INFORMATION_MESSAGE);
		} catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("Error marshalling inventory.");
			JOptionPane.showMessageDialog(null, "No se ha podido exportar el documento", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

    

}
