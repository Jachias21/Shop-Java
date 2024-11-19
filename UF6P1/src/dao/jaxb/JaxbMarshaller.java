package dao.jaxb;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import model.ProductList;


public class JaxbMarshaller {
	
  
    public void init (ProductList inventory) {
		try {
			LocalDate currentDate = LocalDate.now();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	String formattedDate = currentDate.format(formatter);
	    	File file = new File("JaxB/inventory_" + formattedDate + ".xml");
			JAXBContext context = JAXBContext.newInstance(ProductList.class);
			Marshaller marshaller = context.createMarshaller();
			System.out.println("marshalling... ");
			ProductList products = inventory;
			marshaller.marshal(products, file);
			System.out.println("marshalled");
		} catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("Error marshalling inventory.");
		}
	}

    

}
