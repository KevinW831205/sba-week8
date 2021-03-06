package com.github.perscholas;

import com.github.perscholas.utils.DirectoryReference;
import com.github.perscholas.utils.FileReader;
import com.mysql.cj.jdbc.Driver;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class JdbcConfigurator {
    public static void initialize() {
        registerJdbcDriver();
        createDatabase();
        useDatabase();
        createStudentTable();
        createCourseTable();
        createStudentCourse();
        populateStudentTable();
        populateCourseTable();
    }

    private static void createStudentCourse() {
        DatabaseConnection.MYSQL.executeStatement("DROP TABLE if EXISTS studentcourse;");
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory("studentcourses.create-table.sql");
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String creationStatement = fileReader.toString();
        DatabaseConnection.MYSQL.executeStatement(creationStatement);
    }

    private static void populateStudentTable() {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory("students.populate-table.sql");
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String[] seeds = fileReader.toString().split(";");
        Arrays.stream(seeds).forEach(DatabaseConnection.MYSQL::executeStatement);
//        DatabaseConnection.MYSQL.executeStatement(creationStatement);
    }

    private static void populateCourseTable() {
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory("courses.populate-table.sql");
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String[] seeds = fileReader.toString().split(";");
        Arrays.stream(seeds).forEach(DatabaseConnection.MYSQL::executeStatement);

//        DatabaseConnection.MYSQL.executeStatement(creationStatement);
    }

    private static void registerJdbcDriver() {
        // Attempt to register JDBC Driver
        try {
            DriverManager.registerDriver(Driver.class.newInstance());
        } catch (InstantiationException | IllegalAccessException | SQLException e1) {
            throw new Error(e1);
        }
    }

    private static void createDatabase() {
        DatabaseConnection.MYSQL.executeStatement("DROP DATABASE IF EXISTS management_system;");
        DatabaseConnection.MYSQL.executeStatement("CREATE DATABASE IF NOT EXISTS management_system;");
    }

    private static void useDatabase() {
        DatabaseConnection.MYSQL.setDatabase("management_system");
    }

    private static void createStudentTable() {
        DatabaseConnection.MYSQL.executeStatement("DROP TABLE if EXISTS student;");
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory("students.create-table.sql");
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String creationStatement = fileReader.toString();
        DatabaseConnection.MYSQL.executeStatement(creationStatement);
    }

    private static void createCourseTable() {
        DatabaseConnection.MYSQL.executeStatement("DROP TABLE if EXISTS course;");
        File creationStatementFile = DirectoryReference.RESOURCE_DIRECTORY.getFileFromDirectory("courses.create-table.sql");
        FileReader fileReader = new FileReader(creationStatementFile.getAbsolutePath());
        String creationStatement = fileReader.toString();
        DatabaseConnection.MYSQL.executeStatement(creationStatement);
    }
}
