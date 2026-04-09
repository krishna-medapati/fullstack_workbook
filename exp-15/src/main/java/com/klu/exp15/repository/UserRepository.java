package com.klu.exp15.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.exp15.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}