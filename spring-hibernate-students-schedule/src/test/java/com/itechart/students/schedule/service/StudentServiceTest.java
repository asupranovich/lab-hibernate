package com.itechart.students.schedule.service;

import com.itechart.students.schedule.config.AppConfig;
import com.itechart.students.schedule.model.Student;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author asupranovich
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;
    
    @Test
    public void testGetById() {
        Student student = studentService.getById(1L);
        Assert.assertNotNull(student);
    }
    
    @Test
    public void testGetWithAllDataById() {
        Student student = studentService.getWithAllDataById(1L);
        Assert.assertNotNull(student);
    }

}
