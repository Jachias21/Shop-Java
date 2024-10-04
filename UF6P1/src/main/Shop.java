package main;

import model.Product;

import model.Sale;
import model.Amount;
import model.Employee;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.DaoImplFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.io.IOException;
import java.time.LocalDateTime;

public class Shop {
    private Amount cash = new Amount(100.00, "€");
    public List<Product> inventory;
    public int numberProducts;
    private ArrayList<Sale> sales;
    int sale_num = 0;
    private DaoImplFile shopDao = new DaoImplFile();
    

    final static double TAX_RATE = 1.04;

    public Shop() throws IOException, SQLException {
        cash = new Amount(150.0, "€");
        inventory = new ArrayList<>();
        sales = new ArrayList<>();
        readInventory();
    }

    public static void main(String[] args) throws IOException, SQLException {
        Shop shop = new Shop();
        shop.initSession();
        shop.readInventory();

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean exit = false;

        do {
            System.out.println("===========================");
            System.out.println("Menu principal miTienda.com");
            System.out.println("===========================");
            System.out.println("1) Contar caja");
            System.out.println("2) Añadir producto");
            System.out.println("3) Añadir stock");
            System.out.println("4) Marcar producto próxima caducidad");
            System.out.println("5) Ver inventario");
            System.out.println("6) Venta");
            System.out.println("7) Ver ventas");
            System.out.println("8) Ver ventas totales");
            System.out.println("9) Eliminar producto");
            System.out.println("10) Salir del programa");
            System.out.print("Seleccione una opción:");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    shop.showCash();
                    break;

                case 2:
                    shop.addProduct();
                    break;

                case 3:
                    shop.addStock();
                    break;

                case 4:
                    shop.setExpired();
                    break;

                case 5:
                    shop.showInventory();
                    break;

                case 6:
                    shop.sale();
                    break;

                case 7:
                    shop.showSales();
                    break;
                    
                case 8:
                    shop.showSalesValue();
                    break;
                    
                case 9:
                	shop.deleteProduct();
                	break;
                	
                case 10:
                    exit = true;
                    break;

                
            }

        } while (!exit);

        System.out.print("Has salido de la tienda.");
    }

    /**
     * load initial inventory to shop
     * @throws IOException 
     * @throws SQLException 
     */
    
    public void readInventory() throws IOException, SQLException {
    	setInventory(shopDao.getInventory());
    }
   
    
    /**
     * show current total cash
     */
    private void showCash() {
        System.out.println("\nDinero actual: " + cash + "\n");
    }
    
    public  Amount getcash()  {
        return cash; 
}

    /**
     * add a new product to inventory getting data from console
     */
    public void addProduct() {
        if (isInventoryFull()) {
            System.out.println("No se pueden añadir más productos");
            return;
        }
        boolean deluxe = false; 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.println("Origen: ");
        String origin = scanner.nextLine();

        boolean product = productExists(name);

        if (!product) {
            System.out.print("Precio mayorista: ");
            double wholesalerPrice = scanner.nextDouble();
            if (wholesalerPrice > 100) {
            	 deluxe = true; 
            }
            System.out.print("Stock: ");
            int stock = scanner.nextInt();

            inventory.add(new Product(name, origin, new Amount(wholesalerPrice, "€"), true, stock, deluxe));
            numberProducts++;
        } else {
            System.out.print("\nEl producto ya existe en el inventario. Seleccione la opción '3. Añadir Stock'.\n\n");
        }
    }

    /**
     * add stock for a specific product
     */
    public void addStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();
        Product product = findProduct(name);

        if (product != null) {
            // ask for stock
            System.out.print("Seleccione la cantidad a añadir: ");
            int stock = scanner.nextInt();
            int newStock = product.getStock() + stock;
            // update stock product
            product.setStock(newStock);
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());

        } else {
            System.out.println("No se ha encontrado el producto con nombre " + name);
        }
    }

    /**
     * set a product as expired
     */
    private void setExpired() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();

        Product product = findProduct(name);

        product.expire();

        if (product != null) {
            System.out.println("El precio de venta al público del producto " + name + " ha sido actualizado a " + product.getPublicPrice());

        }
    }

    /**
     * show all inventory
     */
    public void showInventory() {
        System.out.println("\n---Contenido actual de la tienda---");
        System.out.print("========================================");
        System.out.println();
        for (Product product : inventory) {
            if (product != null) {
                System.out.print(product);
            } else {
                break;
            }
            System.out.println();
        }
        System.out.println();
    }
    public void showInventorySale() {
        System.out.println("\n---Contenido actual de la tienda---");
        System.out.print("========================================");
        System.out.println();
        for (Product product : inventory) {
            if (product != null) {
                System.out.print(product);
            } else {
                break;
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * make a sale of products to a client
     */
    public void sale() {
        // ask for client name
        Scanner sc = new Scanner(System.in);
        System.out.println("Realizar venta, escribir nombre cliente");
        Client client = new Client(sc.next());
        
        LocalDateTime saleTime = LocalDateTime.now();

        // sale product until input name is not 0
        Amount totalAmount = new Amount(0.0, "€");
        String name = "";
        
        int product_num = 0;

        ArrayList<Product> products = new ArrayList<>();
        int quantity;

        while (!name.equals("0")) {

            sc = new Scanner(System.in);
            showInventorySale();

            System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
            name = sc.nextLine();

            if (name.equals("0")) {
                break;
            }

            Product product = findProduct(name);
            boolean productAvailable = false;

            if (product != null && product.isAvailable()) {

                System.out.println("Introduce la cantidad deseada:");
                quantity = sc.nextInt();

                // if no more stock, set as not available to sale
                if (product.getStock() == 0) {
                    product.setAvailable(false);
                    System.out.println("Producto sin stock.");
                } else if (quantity > product.getStock()) {
                    product.setAvailable(false);
                    System.out.println("Cantidad mayor al stock disponible del producto.");
                    break;
                } else {
                    productAvailable = true;
                    products.add(product);
                    product_num++;
                    totalAmount.setValue(totalAmount.getValue() + (product.getPublicPrice().getValue() * quantity), saleTime);
                    product.setStock(product.getStock() - quantity);
                    System.out.println("Producto añadido con éxito");
                }
            }

            if (!productAvailable) {
                System.out.println("Producto no encontrado");
            }
        }

        // show cost total
        if (totalAmount.getValue() > 0) {
            totalAmount.setValue(totalAmount.getValue() * TAX_RATE, saleTime);
            
            boolean paySucces = client.pay(totalAmount);

            if (paySucces) {
                sales.add(sale_num, new Sale(client, products, totalAmount, saleTime));
                sale_num++;
                cash.setValue(cash.getValue() + totalAmount.getValue(), saleTime);
                System.out.println("Venta realizada con éxito, total: " + totalAmount);
                System.out.println("Dinero restante: "  );
            } else {
                double dueAmount = totalAmount.getValue();
                System.out.println("La venta no pudo ser realizada. Cantidad a deber por parte del cliente: " + dueAmount);
                
                
            }
        } else {
            System.out.println("Venta no realizada.");
       }
   }

    /**
     * show all sales
     */
    private void showSales() {
        System.out.println("Lista de ventas:");
        for (Sale sale : sales) {
            if (sale != null) {
                System.out.println(sale);
                System.out.println(sale.getFormatteDateTime());
            }
        }
        System.out.println("Desea escribir todas las ventas en un fichero?");
        System.out.println("Si-No");
        Scanner scanner = new Scanner(System.in);
		String add = scanner.next();
		if (add.equalsIgnoreCase("Si")) {
			createFile();
		}
    }

    /**
     * show total sales value
     */
    private void showSalesValue() {
        Amount totalSales = new Amount(0.0, "€");
        System.out.print("Precio total de las ventas realizadas: ");
        for (Sale sale : sales) {
            if (sale != null) {
                totalSales.setValue(totalSales.getValue() + sale.getAmount().getValue(), null);
            }
        }
        System.out.println(totalSales);
    }


    /**
     * check if inventory is full or not
     */
    public boolean isInventoryFull() {
        if (numberProducts == 10) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * find product by name
     *
     * @param product name
     */
    public Product findProduct(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) != null && inventory.get(i).getName().equalsIgnoreCase(name)) {
                return inventory.get(i);
            }
        }
        return null;

    }

    public boolean productExists(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) != null && inventory.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;

    }
    
    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre del producto a eliminar: ");
        String name = scanner.nextLine();

        Product product = findProduct(name);

        if (product != null) {
            // Aquí eliminamos el producto de la lista de productos en la instancia de la tienda
            if (inventory.remove(product)) {
                System.out.println("Producto eliminado con éxito.");
            } else {
                System.out.println("No se pudo eliminar el producto.");
            }
        } else {
            System.out.println("No se encontró el producto con nombre " + name);
        }
    }
	public void createFile() {
		
		
		File f = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator +"sales_yyyy-mm-dd.txt");
		try {
			FileWriter fw;
			fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);

			int index = 1;

			for (Sale sale : sales) {
				StringBuilder firstSaleLine = new StringBuilder(index + ";Client=" + sale.getClient() + ";Date=" + sale.getFormatteDateTime() + ";");
				pw.write(firstSaleLine.toString());
				fw.write("\n");
				StringBuilder productLine = new StringBuilder();
				for (Product product : sale.getProducts()) {
					productLine.append(product.getName() + "," + product.getPublicPrice() + ";");
				}
				StringBuilder secondSaleLine = new StringBuilder(index+";Products=" + productLine + ";");
				pw.write(secondSaleLine.toString());
				fw.write("\n");
				StringBuilder amountSaleLine = new StringBuilder(index+";Amount="+ sale.getAmount()+ ";");
				pw.write(amountSaleLine.toString());
				fw.write("\n");
				index++;

			}
			pw.close();
			fw.close();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
        
        public boolean initSession() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================");
        System.out.println("----Login miTienda.com-----");
        System.out.println("===========================");

        boolean logged = false;
        Employee employee = new Employee(null);

        do {
            System.out.print("Número de empleado: ");
            int employeeId = scanner.nextInt();
            System.out.print("Contraseña: ");
            String password = scanner.next();

            logged = employee.login(employeeId, password);

            if (!logged) {
                System.out.println("Fallo, vuelve a intentar.");
            } else {
                System.out.println("Inicio de sesión exitoso. ¡Bienvenido!");
            }
        } while (!logged);

        return logged;
    }
        public void setInventory(List<Product> info) {
            this.inventory = info;
        } 

}
