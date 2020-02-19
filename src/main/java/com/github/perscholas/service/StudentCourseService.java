package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentCourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseService implements StudentCourseDao {


    private final DatabaseConnection dbc;

    public StudentCourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public StudentCourseService() {
        this(DatabaseConnection.MYSQL);
    }

    @Override
    public List<CourseInterface> getStudentCourse(String studentEmail) {

        ResultSet result = dbc.executeQuery("SELECT * FROM studentcourse WHERE studentcourse.email = '"+studentEmail+"'");

        List<CourseInterface> courseList = new ArrayList<>();
        try {
            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String instructor = result.getString("instructor");
                CourseInterface course = new Course(id, name, instructor);
                courseList.add(course);
            }
        } catch (SQLException se) {
            throw new Error(se);
        }


        return courseList;
    }
}
