package dao.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import model.Product;

public class DomWriter {
    private Document document;

    public DomWriter() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println("ERROR generating document");
        }
    }

    public void generateDocument(List<Product> inventory) {
        // Create root element
        Element products = document.createElement("products");
        products.setAttribute("total", String.valueOf(inventory.size()));
        document.appendChild(products);

        int i = 1;
        for (Product pr_inventory : inventory) {
            // Create product element with attributes
            Element product = document.createElement("product");
            product.setAttribute("id", String.valueOf(i));
            products.appendChild(product);

            // Create name element
            Element name = document.createElement("name");
            name.setTextContent(pr_inventory.getName());
            product.appendChild(name);

            // Create price element with currency attribute
            Element price = document.createElement("price");
            price.setAttribute("currency", "€");
            price.setTextContent(String.valueOf(pr_inventory.getWholesalerPrice().getValue()));
            product.appendChild(price);

            // Create stock element
            Element stock = document.createElement("stock");
            stock.setTextContent(String.valueOf(pr_inventory.getStock()));
            product.appendChild(stock);
            i++;
        }

        generateXml();
    }

    private void generateXml() {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(document);

            //Set up file with current date in name
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);
            File file = new File("SaxDom/inventory_" + formattedDate + ".xml");

            //Write in file
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            StreamResult result = new StreamResult(pw); 
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(null, "Archivo exportado", "Exported", JOptionPane.PLAIN_MESSAGE);

        } catch (IOException e) {
            System.out.println("Error creating writer file");
            JOptionPane.showMessageDialog(null, "No se ha podido exportar", "ERROR", JOptionPane.ERROR_MESSAGE);

        } catch (TransformerException e) {
            System.out.println("Error transforming document");
            JOptionPane.showMessageDialog(null, "No se ha podido transformar el documento", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
