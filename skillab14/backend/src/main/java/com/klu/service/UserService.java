package com.klu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.entity.User;
import com.klu.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // ✅ Register
    public User register(User user) {
        return repo.save(user);
    }

    // ✅ Login (FIXED — no exception)
    public User login(String username, String password) {

        java.util.Optional<User> optionalUser = repo.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        return null; // important
    }

    // ✅ Get user
    public User getUser(Long id) {
        return repo.findById(id).orElse(null);
    }
}