package com.example.GradingSystem3.Models;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String email;
    private final String name;
    private final String major;
    private final List<Course> courses;

    public Student(int id, String email, String name, String major, List<Course> courses) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.major = major;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", courses=" + courses +
                '}';
    }
}
