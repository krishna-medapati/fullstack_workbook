package com.fsad.skill13.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fsad.skill13.backend.model.User;
import com.fsad.skill13.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

@Autowired
private UserService service;

@GetMapping
public List<User> getUsers(){
return service.getAllUsers();
}

@PostMapping
public User addUser(@RequestBody User user){
return service.saveUser(user);
}

}