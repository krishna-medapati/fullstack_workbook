package com.klu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.entity.Student;
import com.klu.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repo;

    @Override
    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existing = repo.findById(id).orElseThrow();
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setCourse(student.getCourse());
        return repo.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}