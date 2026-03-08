package com.klu.main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Student;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext cn =
                new AnnotationConfigApplicationContext(AppConfig.class);
        Student s1 = cn.getBean(Student.class);
        s1.display();
 
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Student s2 = context.getBean(Student.class);
        s2.display();
    }
}
