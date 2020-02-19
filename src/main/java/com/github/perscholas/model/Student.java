package com.github.perscholas.model;

import java.util.ArrayList;
import java.util.List;

// TODO - Annotate and Implement respective interface and define behaviors
public class Student implements StudentInterface {
    private String email;
    private String name;
    private String password;
    private List<CourseInterface> courses;

    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.courses = new ArrayList<>();
    }


    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<CourseInterface> getCourses() {
        return courses;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setCourseInterface(List<CourseInterface> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", courses=" + courses +
                '}';
    }
}
