package experiment2;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class ProductApp {
    
    private static SessionFactory factory;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        try {
            // Initialize Hibernate
            initializeHibernate();
            
            // Main menu loop
            boolean running = true;
            while (running) {
                displayMenu();
                int choice = getIntInput("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        insertProduct();
                        break;
                    case 2:
                        insertMultipleProducts();
                        break;
                    case 3:
                        retrieveProductById();
                        break;
                    case 4:
                        displayAllProducts();
                        break;
                    case 5:
                        updateProduct();
                        break;
                    case 6:
                        deleteProduct();
                        break;
                    case 7:
                        running = false;
                        System.out.println("\n✅ Thank you for using the Inventory System!");
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please try again.\n");
                }
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
            scanner.close();
        }
    }
    
    // Initialize Hibernate Configuration
    private static void initializeHibernate() {
        Configuration cfg = new Configuration();
        
        Properties properties = new Properties();
        properties.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/fsad_db");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "Vanitas@41");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.FORMAT_SQL, "true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        
        cfg.setProperties(properties);
        cfg.addAnnotatedClass(Product.class);
        factory = cfg.buildSessionFactory();
        
        System.out.println("✅ SessionFactory created successfully!\n");
    }
    
    // Display Main Menu
    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       RETAIL INVENTORY MANAGEMENT SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Insert Single Product");
        System.out.println("2. Insert Multiple Products");
        System.out.println("3. Retrieve Product by ID");
        System.out.println("4. Display All Products");
        System.out.println("5. Update Product");
        System.out.println("6. Delete Product");
        System.out.println("7. Exit");
        System.out.println("=".repeat(50));
    }
    
    // 1. INSERT SINGLE PRODUCT
    private static void insertProduct() {
        System.out.println("\n=== INSERT NEW PRODUCT ===");
        
        String name = getStringInput("Enter product name: ");
        String description = getStringInput("Enter description: ");
        double price = getDoubleInput("Enter price: ");
        int quantity = getIntInput("Enter quantity: ");
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Product product = new Product(name, description, price, quantity);
            session.save(product);
            
            tx.commit();
            System.out.println("✅ Product inserted successfully with ID: " + product.getId());
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error inserting product: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    // 2. INSERT MULTIPLE PRODUCTS
    private static void insertMultipleProducts() {
        System.out.println("\n=== INSERT MULTIPLE PRODUCTS ===");
        
        int count = getIntInput("How many products do you want to insert? ");
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            for (int i = 1; i <= count; i++) {
                System.out.println("\n--- Product " + i + " ---");
                String name = getStringInput("Enter product name: ");
                String description = getStringInput("Enter description: ");
                double price = getDoubleInput("Enter price: ");
                int quantity = getIntInput("Enter quantity: ");
                
                Product product = new Product(name, description, price, quantity);
                session.save(product);
            }
            
            tx.commit();
            System.out.println("✅ " + count + " products inserted successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error inserting products: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    // 3. RETRIEVE PRODUCT BY ID
    private static void retrieveProductById() {
        System.out.println("\n=== RETRIEVE PRODUCT BY ID ===");
        
        long id = getLongInput("Enter product ID: ");
        
        Session session = factory.openSession();
        
        try {
            Product product = session.get(Product.class, id);
            
            if (product != null) {
                System.out.println("\n✅ Product Found:");
                System.out.println("─".repeat(50));
                System.out.println(product);
                System.out.println("─".repeat(50));
            } else {
                System.out.println("❌ Product with ID " + id + " not found!");
            }
        } catch (Exception e) {
            System.err.println("❌ Error retrieving product: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    // 4. DISPLAY ALL PRODUCTS
    private static void displayAllProducts() {
        System.out.println("\n=== ALL PRODUCTS ===");
        
        Session session = factory.openSession();
        
        try {
            List<Product> products = session.createQuery("FROM Product", Product.class).list();
            
            if (products.isEmpty()) {
                System.out.println("❌ No products found in the database!");
            } else {
                System.out.println("Total Products: " + products.size());
                System.out.println("─".repeat(80));
                System.out.printf("%-5s %-20s %-30s %-10s %-10s%n", 
                    "ID", "Name", "Description", "Price", "Quantity");
                System.out.println("─".repeat(80));
                
                for (Product p : products) {
                    System.out.printf("%-5d %-20s %-30s %-10.2f %-10d%n",
                        p.getId(), p.getName(), p.getDescription(), 
                        p.getPrice(), p.getQuantity());
                }
                System.out.println("─".repeat(80));
            }
        } catch (Exception e) {
            System.err.println("❌ Error fetching products: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    // 5. UPDATE PRODUCT
    private static void updateProduct() {
        System.out.println("\n=== UPDATE PRODUCT ===");
        
        long id = getLongInput("Enter product ID to update: ");
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Product product = session.get(Product.class, id);
            
            if (product == null) {
                System.out.println("❌ Product with ID " + id + " not found!");
                return;
            }
            
            System.out.println("\nCurrent Product Details:");
            System.out.println(product);
            
            System.out.println("\nWhat do you want to update?");
            System.out.println("1. Name");
            System.out.println("2. Description");
            System.out.println("3. Price");
            System.out.println("4. Quantity");
            System.out.println("5. All fields");
            
            int choice = getIntInput("Enter choice: ");
            
            switch (choice) {
                case 1:
                    String name = getStringInput("Enter new name: ");
                    product.setName(name);
                    break;
                case 2:
                    String desc = getStringInput("Enter new description: ");
                    product.setDescription(desc);
                    break;
                case 3:
                    double price = getDoubleInput("Enter new price: ");
                    product.setPrice(price);
                    break;
                case 4:
                    int quantity = getIntInput("Enter new quantity: ");
                    product.setQuantity(quantity);
                    break;
                case 5:
                    product.setName(getStringInput("Enter new name: "));
                    product.setDescription(getStringInput("Enter new description: "));
                    product.setPrice(getDoubleInput("Enter new price: "));
                    product.setQuantity(getIntInput("Enter new quantity: "));
                    break;
                default:
                    System.out.println("❌ Invalid choice!");
                    return;
            }
            
            session.update(product);
            tx.commit();
            
            System.out.println("\n✅ Product updated successfully!");
            System.out.println("Updated Product: " + product);
            
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error updating product: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    // 6. DELETE PRODUCT
    private static void deleteProduct() {
        System.out.println("\n=== DELETE PRODUCT ===");
        
        long id = getLongInput("Enter product ID to delete: ");
        
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            Product product = session.get(Product.class, id);
            
            if (product == null) {
                System.out.println("❌ Product with ID " + id + " not found!");
                return;
            }
            
            System.out.println("\nProduct to be deleted:");
            System.out.println(product);
            
            String confirm = getStringInput("Are you sure you want to delete? (yes/no): ");
            
            if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
                session.delete(product);
                tx.commit();
                System.out.println("✅ Product deleted successfully!");
            } else {
                System.out.println("❌ Deletion cancelled!");
            }
            
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("❌ Error deleting product: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    // Helper Methods for Input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid number.");
            }
        }
    }
    
    private static long getLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                long value = Long.parseLong(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid number.");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("❌ Price cannot be negative!");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid number.");
            }
        }
    }
}