package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.CourseDao;
import com.github.perscholas.model.CourseInterface;

import java.sql.ResultSet;
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

        return null;
    }
}
