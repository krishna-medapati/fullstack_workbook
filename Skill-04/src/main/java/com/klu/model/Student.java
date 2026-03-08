package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
    private int studentId;
    private String name;
    private String course;
    private int year;

    public Student(
            @Value("101") int studentId,
            @Value("Vijay Kumar") String name,
            @Value("CSE") String course,
            @Value("3") int year) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.year = year;
    }

    @Value("Full Stack Development")
    public void setCourse(String course) {
        this.course = course;
    }

    @Value("4")
    public void setYear(int year) {
        this.year = year;
    }

    public void display() {
        System.out.println("Student Details:");
        System.out.println("StudentId : " + studentId);
        System.out.println("Name      : " + name);
        System.out.println("Course    : " + course);
        System.out.println("Year      : " + year);
    }
}
