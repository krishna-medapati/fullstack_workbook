package com.fsad.skill16.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fsad.skill16.backend.model.Student;
import com.fsad.skill16.backend.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService service;

    @Operation(summary = "Add new student")
    @ApiResponse(responseCode = "200", description = "Student added successfully")
    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return service.addStudent(student);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public List<Student> getStudents(){
        return service.getAllStudents();
    }

    @Operation(summary = "Get student by ID")
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id){
        return service.getStudentById(id);
    }

    @Operation(summary = "Update student")
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student){

        return service.updateStudent(id, student);
    }

    @Operation(summary = "Delete student")
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){

        service.deleteStudent(id);
        return "Student Deleted Successfully";
    }
}