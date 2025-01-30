package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Amount;
import model.Employee;
import model.Product;

public class DaoImplHibernate implements dao{
	private Session session;
    private Transaction tx;

    /**
     * Inicializa la sesión de Hibernate.
     */
    public void connect() throws SQLException  {
        if (session == null || !session.isOpen()) {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            org.hibernate.SessionFactory sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Successul connection");
        }
    }

    /**
     * Finaliza la sesión de Hibernate.
     */
    public void disconnect() throws SQLException  {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
    
    @Override
    public List<Product> getInventory() throws SQLException, FileNotFoundException, IOException {
        List<Product> inventory = new ArrayList<>(); 

        try {
            connect();
            tx = session.beginTransaction();

            
            inventory = session.createQuery("FROM Product", Product.class).list();

            for (Product product : inventory) {
                product.setWholesalerPrice(new Amount(product.getPrice(), "€"));
                product.setPublicPrice(new Amount(product.getPrice() * 2, "€"));
            }

            tx.commit(); 
        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback(); 
                } catch (IllegalStateException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return inventory; 
    }

	    
	@Override
	public boolean writeInventory(List<Product> inventory) throws SQLException {
		
		try {
			connect();
			tx = session.beginTransaction();

			String sql = "INSERT INTO historical_inventory (id_product, name, price, stock, available, created_at) " +
						 "VALUES (:id_product, :name, :price, :stock, :available, :created_at)";
			for (Product product : inventory) {
				session.createNativeQuery(sql)
					   .setParameter("id_product", product.getId())
					   .setParameter("name", product.getName())
					   .setParameter("price", product.getWholesalerPrice().getValue()) // Ajusta si necesitas otro precio
					   .setParameter("stock", product.getStock())
					   .setParameter("available", product.isAvailable())
					   .setParameter("created_at", LocalDateTime.now())
					   .executeUpdate();
			}

			tx.commit();
			System.out.println("Inventario guardado en historical_inventory");
			return true;
		} catch (Exception e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (IllegalStateException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
	}



	@Override
	public boolean addProduct(Product product) throws SQLException {
	    try {
	        connect();
	        tx = session.beginTransaction(); 
	        
	        if (product == null) {
	            throw new IllegalArgumentException("El producto no puede ser nulo");
	        }
	        session.save(product);

	        tx.commit();
	        System.out.println("Producto añadido correctamente: " + product);
	    } catch (Exception e) {
	        if (tx != null) {
	            try {
	                tx.rollback(); 
	            } catch (IllegalStateException e1) {
	                e1.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
		return true;
	}


	public boolean deleteProduct(Product product) throws SQLException {
	    try {
	        connect();
	        tx = session.beginTransaction();

	        if (product == null) {
	            throw new IllegalArgumentException("El producto no puede ser nulo");
	        }

	        session.delete(product);

	        tx.commit();
	        System.out.println("Producto eliminado correctamente: " + product);
	    } catch (Exception e) {
	        if (tx != null) {
	            try {
	                tx.rollback();
	            } catch (IllegalStateException e1) {
	                e1.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    return true;
	}

	@Override
	public boolean updateProduct(Product product) throws SQLException {
		try {
			connect();
			tx = session.beginTransaction();
			if (product == null) {
				throw new IllegalArgumentException("El producto no puede ser nulo");
			}
			session.update(product);
			tx.commit();
			 System.out.println("Producto actualizado correctamente: " + product);
			} catch (Exception e) {
		        if (tx != null) {
		            try {
		                tx.rollback();
		            } catch (IllegalStateException e1) {
		                e1.printStackTrace();
		            }
		        }
		        e.printStackTrace();
		    } finally {
		        disconnect();
		    }
		    return true;
		}

	@Override
	public Employee getEmployee(int employeeID, String password) {
		// TODO Auto-generated method stub
		return null;
	}


}
