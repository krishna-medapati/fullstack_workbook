package com.skill3.HibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.skill3.Product.Product;

public class HibernateUtil {

    private static final SessionFactory factory;

    static {
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Product.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
