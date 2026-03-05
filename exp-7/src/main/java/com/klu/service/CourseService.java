package com.klu.service;

import com.klu.model.Course;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private Map<Integer, Course> courseMap = new HashMap<>();

    public Course addCourse(Course course) {
        if (courseMap.containsKey(course.getCourseId())) return null;
        courseMap.put(course.getCourseId(), course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseMap.values());
    }

    public Course getCourseById(int id) {
        return courseMap.get(id);
    }

    public Course updateCourse(int id, Course course) {
        if (!courseMap.containsKey(id)) return null;
        course.setCourseId(id);
        courseMap.put(id, course);
        return course;
    }

    public boolean deleteCourse(int id) {
        if (!courseMap.containsKey(id)) return false;
        courseMap.remove(id);
        return true;
    }

    public List<Course> searchByTitle(String title) {
        return courseMap.values().stream()
            .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
    }
}
