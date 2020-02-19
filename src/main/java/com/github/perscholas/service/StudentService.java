package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {

    private final DatabaseConnection dbc;
    private final CourseService courseService;
    private final StudentCourseService studentCourseService;

    public StudentService(DatabaseConnection dbc, CourseService courseService, StudentCourseService studentCourseService) {
        this.dbc = dbc;
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }

    public StudentService() {
        this(DatabaseConnection.MYSQL, new CourseService(), new StudentCourseService());
    }

    @Override
    public List<StudentInterface> getAllStudents() {
        ResultSet result = dbc.executeQuery("SELECT * FROM student");
        List<StudentInterface> list = new ArrayList<>();
        try {
            while (result.next()) {
                String studentEmail = result.getString("email");
                String name = result.getString("name");
                String password = result.getString("password");
                List<CourseInterface> courses = studentCourseService.getStudentCourse(studentEmail);
                StudentInterface student = new Student(studentEmail, name, password);
                student.setCourseInterface(courses);
                list.add(student);
            }
        } catch (SQLException se) {
            throw new Error(se);
        }

        return list;
    }

    @Override
    public StudentInterface getStudentByEmail(String studentEmail) {
        List<StudentInterface> students = getAllStudents();

        return students.stream()
                .filter(s -> s.getEmail().equals(studentEmail))
                .findFirst()
                .orElseGet(() -> null);
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
        StudentInterface student = getStudentByEmail(studentEmail);
        return student.getPassword().equals(password);
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
        CourseInterface courseToAdd = courseService.getAllCourses()
                .stream()
                .filter(c -> c.getId() == courseId)
                .findFirst()
                .orElse(null);


        dbc.executeStatement("insert into StudentCourse (email, id, name, instructor) values ('"
                + studentEmail + "','"
                + courseToAdd.getId() + "', '"
                + courseToAdd.getName() + "', '"
                + courseToAdd.getInstructor() + "')"
        );
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return studentCourseService.getStudentCourse(studentEmail);
    }
}
