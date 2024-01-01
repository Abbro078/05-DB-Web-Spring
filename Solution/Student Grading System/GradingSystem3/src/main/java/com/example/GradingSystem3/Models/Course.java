package com.example.GradingSystem3.Models;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class Course implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String name;
    private final Integer mark;

    public Course(Integer id, String name, Integer mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}
