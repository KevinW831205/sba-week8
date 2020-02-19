package com.github.perscholas.model;

import java.util.List;

public interface StudentInterface {
    String getEmail();
    String getName();
    String getPassword();
    List<CourseInterface> getCourses();

    void setEmail(String email);
    void setName(String name);
    void setPassword(String password);
    void setCourseInterface(List<CourseInterface> courses);
}
