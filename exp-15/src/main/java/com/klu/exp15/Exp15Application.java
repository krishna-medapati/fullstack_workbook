package com.klu.exp15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.klu.exp15.entity.User;
import com.klu.exp15.repository.UserRepository;

@SpringBootApplication
public class Exp15Application {

    public static void main(String[] args) {
        SpringApplication.run(Exp15Application.class, args);
    }

    // 🔥 Insert default users into DB
    @Bean
    CommandLineRunner run(UserRepository repo, PasswordEncoder encoder) {
        return args -> {

            // ADMIN user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("1234")); // ✅ encoded password
            admin.setRole("ADMIN");

            // EMPLOYEE user
            User emp = new User();
            emp.setUsername("emp");
            emp.setPassword(encoder.encode("1234")); // ✅ encoded password
            emp.setRole("EMPLOYEE");

            repo.save(admin);
            repo.save(emp);

            System.out.println("✅ Users inserted into DB");
        };
    }
}