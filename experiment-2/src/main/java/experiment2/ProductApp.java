package experiment2;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class ProductApp {
    
    public static void main(String[] args) {
        SessionFactory factory = null;
        
        try {
            // Create Configuration object
            Configuration cfg = new Configuration();
            
            // Hibernate settings (instead of hibernate.cfg.xml)
            Properties properties = new Properties();
            properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/fsad_db");
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "Vanitas@41");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            properties.setProperty(Environment.SHOW_SQL, "true");
            properties.setProperty(Environment.FORMAT_SQL, "true");
            properties.setProperty(Environment.HBM2DDL_AUTO, "update");
            
            // Apply properties to configuration
            cfg.setProperties(properties);
            
            // Add annotated entity class
            cfg.addAnnotatedClass(Product.class);
            
            // Build SessionFactory
            factory = cfg.buildSessionFactory();
            
            System.out.println("✅ SessionFactory created successfully!\n");
            
            // CREATE - Insert new products
            System.out.println("=== INSERTING PRODUCTS ===");
            Session session1 = factory.openSession();
            Transaction tx1 = session1.beginTransaction();
            
            Product p1 = new Product("Laptop", "Dell Inspiron 15", 55000, 10);
            Product p2 = new Product("Mouse", "Logitech Wireless", 500, 50);
            Product p3 = new Product("Keyboard", "Mechanical RGB", 2000, 30);
            Product p4 = new Product("Monitor", "Samsung 24 inch", 12000, 15);
            Product p5 = new Product("Headphones", "Sony Noise Cancelling", 3500, 25);
            
            session1.save(p1);
            session1.save(p2);
            session1.save(p3);
            session1.save(p4);
            session1.save(p5);
            
            tx1.commit();
            session1.close();
            System.out.println("✅ 5 Products inserted successfully!\n");
            
            // READ - Retrieve product by ID
            System.out.println("=== RETRIEVING PRODUCT BY ID ===");
            Session session2 = factory.openSession();
            Product product = session2.get(Product.class, 1L);
            System.out.println("Retrieved: " + product);
            session2.close();
            System.out.println();
            
            // UPDATE - Update price and quantity
            System.out.println("=== UPDATING PRODUCT ===");
            Session session3 = factory.openSession();
            Transaction tx3 = session3.beginTransaction();
            
            Product updateProduct = session3.get(Product.class, 2L);
            System.out.println("Before Update: " + updateProduct);
            updateProduct.setPrice(600);
            updateProduct.setQuantity(45);
            session3.update(updateProduct);
            
            tx3.commit();
            System.out.println("After Update: " + updateProduct);
            session3.close();
            System.out.println("✅ Product updated successfully!\n");
            
            // DELETE - Remove product
            System.out.println("=== DELETING PRODUCT ===");
            Session session4 = factory.openSession();
            Transaction tx4 = session4.beginTransaction();
            
            Product delProduct = session4.get(Product.class, 3L);
            System.out.println("Deleting: " + delProduct);
            session4.delete(delProduct);
            
            tx4.commit();
            session4.close();
            System.out.println("✅ Product deleted successfully!\n");
            
            // Display remaining products
            System.out.println("=== REMAINING PRODUCTS ===");
            Session session5 = factory.openSession();
            java.util.List<Product> products = session5.createQuery("from Product", Product.class).list();
            System.out.println("Total Products: " + products.size());
            products.forEach(System.out::println);
            session5.close();
            
            System.out.println("\n✅ All CRUD operations completed successfully!");
            
        } catch (Exception e) {
            System.err.println("❌ Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}