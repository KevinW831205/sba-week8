package com.github.perscholas.dao;

import com.github.perscholas.model.CourseInterface;

import java.util.List;

public interface StudentCourseDao {
    List<CourseInterface> getStudentCourse(String studentEmail);
}
