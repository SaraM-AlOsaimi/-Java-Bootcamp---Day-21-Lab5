package com.example.student.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    // Student Class: ID, name, age, degree, GPA.
    private int id;
    private String name;
    private int age;
    private String degree;
    private double GPA;


}
