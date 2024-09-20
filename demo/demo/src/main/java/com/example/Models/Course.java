package com.example.Models;

public class Course {
    String name;
    Long id;
    Long teacherID;

    public Course(String name, Long id, Long teacherID) {
        this.name = name;
        this.id = id;
        this.teacherID = teacherID;
    }

    public Course(String name, Long teacherID) {
        this.name = name;
        this.teacherID = teacherID;
    }

    public Long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Long teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
