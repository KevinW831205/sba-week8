package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class CourseService implements CourseDao {
    private final DatabaseConnection dbc;

    public CourseService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public CourseService() {
        this(DatabaseConnection.MYSQL);
    }


    @Override
    public List<CourseInterface> getAllCourses() {
        ResultSet result = dbc.executeQuery("SELECT * FROM courses");

        List<CourseInterface> courseList = new ArrayList<>();
        try {
            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String instructor = result.getString("instructor");
                CourseInterface course = new Course(id, name, instructor);
                courseList.add(course);
            }
        } catch(SQLException se) {
            throw new Error(se);
        }


        return null;
    }
}
