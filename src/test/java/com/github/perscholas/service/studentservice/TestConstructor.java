package com.github.perscholas.service.studentservice;

import com.github.perscholas.model.Student;
import com.github.perscholas.service.StudentService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonhunter
 * @created 02/12/2020 - 8:47 PM
 */ // TODO - Define tests
public class TestConstructor {
    @Test
    public void testConstructor(){
        StudentService studentService = new StudentService();
        Assert.assertNotNull(studentService);
    }

}
