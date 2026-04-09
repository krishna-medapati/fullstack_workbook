package com.fsad.skill16.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsad.skill16.backend.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}