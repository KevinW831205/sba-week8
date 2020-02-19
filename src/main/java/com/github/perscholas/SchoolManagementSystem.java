package com.github.perscholas;

import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.Course;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.service.CourseService;
import com.github.perscholas.service.StudentCourseService;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.IOConsole;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchoolManagementSystem implements Runnable {
    private static final IOConsole console = new IOConsole();

    @Override
    public void run() {
        String smsDashboardInput = getSchoolManagementSystemDashboardInput();
        String studentDashboardInput = "";
        do {
            try {
                if ("login".equals(smsDashboardInput)) {
                    StudentDao studentService = new StudentService(DatabaseConnection.MYSQL, new CourseService(), new StudentCourseService());
                    String studentEmail = console.getStringInput("Enter your email:");
                    String studentPassword = console.getStringInput("Enter your password:");
                    Boolean isValidLogin = studentService.validateStudent(studentEmail, studentPassword);
                    if (isValidLogin) {
                        studentDashboardInput = getStudentDashboardInput();
                        if ("register".equals(studentDashboardInput)) {
                            Integer courseId = getCourseRegistryInput();
                            System.out.println(studentEmail + "   " + courseId);
                            studentService.registerStudentToCourse(studentEmail, courseId);
                        }
                        if("logout".equals(studentDashboardInput)){
                            break;
                        }

                        studentDashboardInput = getViewInput();
                        if ("view".equals(studentDashboardInput)) {
                            List<CourseInterface> courses = studentService.getStudentCourses(studentEmail);
                            System.out.println("Course List: ");
                            courses.forEach(System.out::println);
                        }
                        if("logout".equals(studentDashboardInput)){
                            break;
                        }

                    }
                } else {
                    break;
                }
            } catch (NullPointerException e){
                System.out.println("cant find");
                continue;
            }

        } while (true);
    }

    private String getSchoolManagementSystemDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the School Management System Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ login ], [ logout ]")
                .toString());
    }

    private String getStudentDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Student Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ register ], [ logout]")
                .toString());
    }

    private String getViewInput() {
        return console.getStringInput(new StringBuilder()
                .append("View Courses?")
                .append("\n[view], [logout]")
                .toString());
    }

    private Integer getCourseRegistryInput() {
        CourseService courseService = new CourseService();
        List<Integer> listOfCoursesIds;
        listOfCoursesIds = courseService.getAllCourses().stream().map(CourseInterface::getId).collect(Collectors.toList());
        return console.getIntegerInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t" + listOfCoursesIds.toString())
                .toString());
    }
}
