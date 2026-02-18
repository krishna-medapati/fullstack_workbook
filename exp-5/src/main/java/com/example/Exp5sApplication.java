package com.klu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Exp5sApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Exp5sApplication.class, args);
        Student student = context.getBean(Student.class);
        System.out.println("Student Details:");
        System.out.println(student);
    }
}
