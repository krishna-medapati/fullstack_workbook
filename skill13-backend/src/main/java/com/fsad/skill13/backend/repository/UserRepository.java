package com.fsad.skill13.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fsad.skill13.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}