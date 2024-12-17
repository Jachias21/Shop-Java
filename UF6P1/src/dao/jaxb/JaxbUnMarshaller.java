package dao.jaxb;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.Product;
import model.ProductList;

public class JaxbUnMarshaller {

    public ProductList unmarshalProducts(String filePath) {
        ProductList products = null;

        try {
            JAXBContext context = JAXBContext.newInstance(ProductList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            System.out.println("Unmarshalling...");
            products = (ProductList) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se ha podido importar", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        if (products == null) {
            System.out.println("Error unmarshalling");
            JOptionPane.showMessageDialog(null, "No se ha podido transformar el documento", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            //System.out.println("Inventario cargado");
        }

        return products;
    }
}
