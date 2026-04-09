package com.skill3.ProductMain;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.skill3.HibernateUtil.HibernateUtil;
import com.skill3.Product.Product;

public class ProductMain {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Insert data only once
        Transaction tx = session.beginTransaction();
        Long count = session.createQuery("select count(*) from Product", Long.class)
                            .uniqueResult();

        if (count == 0) {
            session.save(new Product("Pen", "Stationery", 10, 100));
            session.save(new Product("Pencil", "Stationery", 5, 200));
            session.save(new Product("Notebook", "Stationery", 50, 50));
            session.save(new Product("Mouse", "Electronics", 500, 20));
            session.save(new Product("Keyboard", "Electronics", 800, 10));
            session.save(new Product("Charger", "Electronics", 600, 0));
        }
        tx.commit();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n HQL Operations on DB ");
            System.out.println("1. Sort Products by Price (ASC)");
            System.out.println("2. Sort Products by Price (DESC)");
            System.out.println("3. Sort Products by Quantity");
            System.out.println("4. Pagination");
            System.out.println("5. Total Products Count");
            System.out.println("6. Available Products (quantity > 0)");
            System.out.println("7. Group By Category");
            System.out.println("8. Min & Max Price");
            System.out.println("9. Price Between 100 and 700");
            System.out.println("10. LIKE Operations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    session.createQuery(
                            "from Product order by price asc", Product.class)
                            .list().forEach(System.out::println);
                    break;

                case 2:
                    session.createQuery(
                            "from Product order by price desc", Product.class)
                            .list().forEach(System.out::println);
                    break;

                case 3:
                    session.createQuery(
                            "from Product order by quantity desc", Product.class)
                            .list().forEach(System.out::println);
                    break;

                case 4:
                    Query<Product> p1 = session.createQuery("from Product", Product.class);
                    p1.setFirstResult(0);
                    p1.setMaxResults(3);
                    System.out.println("First 3 Products:");
                    p1.list().forEach(System.out::println);

                    Query<Product> p2 = session.createQuery("from Product", Product.class);
                    p2.setFirstResult(3);
                    p2.setMaxResults(3);
                    System.out.println("Next 3 Products:");
                    p2.list().forEach(System.out::println);
                    break;

                case 5:
                    Long total = session.createQuery(
                            "select count(*) from Product", Long.class)
                            .uniqueResult();
                    System.out.println("Total Products: " + total);
                    break;

                case 6:
                    Long available = session.createQuery(
                            "select count(*) from Product where quantity > 0", Long.class)
                            .uniqueResult();
                    System.out.println("Available Products: " + available);
                    break;

                case 7:
                    List<Object[]> grp = session.createQuery(
                            "select category, count(*) from Product group by category",
                            Object[].class).list();
                    for (Object[] row : grp) {
                        System.out.println(row[0] + " : " + row[1]);
                    }
                    break;

                case 8:
                    Object[] mm = session.createQuery(
                            "select min(price), max(price) from Product",
                            Object[].class).uniqueResult();
                    System.out.println("Min Price: " + mm[0]);
                    System.out.println("Max Price: " + mm[1]);
                    break;

                case 9:
                    session.createQuery(
                            "from Product where price between 100 and 700",
                            Product.class).list().forEach(System.out::println);
                    break;

                case 10:
                    System.out.println("Names starting with P:");
                    session.createQuery(
                            "from Product where name like 'P%'",
                            Product.class).list().forEach(System.out::println);

                    System.out.println("Names ending with r:");
                    session.createQuery(
                            "from Product where name like '%r'",
                            Product.class).list().forEach(System.out::println);

                    System.out.println("Names containing 'note':");
                    session.createQuery(
                            "from Product where name like '%note%'",
                            Product.class).list().forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        session.close();
        HibernateUtil.getSessionFactory().close();
        sc.close();
    }
}
