package com.klu;

import org.springframework.stereotype.Component;

@Component
public class Certification {
    private int id = 101;
    private String name = "AWS Certified Developer";
    private String dateOfCompletion = "2024-12-15";

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDateOfCompletion() { return dateOfCompletion; }

    @Override
    public String toString() {
        return "Certification [id=" + id + ", name=" + name + ", dateOfCompletion=" + dateOfCompletion + "]";
    }
}
