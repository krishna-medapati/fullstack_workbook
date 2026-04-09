package com.fsad.skill13.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsad.skill13.backend.model.User;
import com.fsad.skill13.backend.repository.UserRepository;

@Service
public class UserService {

@Autowired
private UserRepository repository;

public List<User> getAllUsers(){
return repository.findAll();
}

public User saveUser(User user){
return repository.save(user);
}

}