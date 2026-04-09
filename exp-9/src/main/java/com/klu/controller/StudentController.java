package com.klu.controller;

import com.klu.exception.InvalidInputException;
import com.klu.exception.StudentNotFoundException;
import com.klu.model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {

    private static final Map<Integer, Student> students = new HashMap<>();

    static {
        students.put(1, new Student(1, "Alice", "CSE"));
        students.put(2, new Student(2, "Bob", "ECE"));
        students.put(3, new Student(3, "Charlie", "MECH"));
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable String id) {
        try {
            int studentId = Integer.parseInt(id);
            if (studentId <= 0) {
                throw new InvalidInputException("Student ID must be a positive number. Got: " + studentId);
            }
            Student student = students.get(studentId);
            if (student == null) {
                throw new StudentNotFoundException("No student found with ID: " + studentId);
            }
            return student;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: '" + id + "' is not a valid student ID. Please enter a number.");
        }
    }
}
