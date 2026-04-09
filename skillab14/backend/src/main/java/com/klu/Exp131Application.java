package com.klu;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.klu.controller",
    "com.klu.service",
    "com.klu.repository",
    "com.klu.entity"
})
public class Exp131Application {
    public static void main(String[] args) {
        SpringApplication.run(Exp131Application.class, args);
    }
}