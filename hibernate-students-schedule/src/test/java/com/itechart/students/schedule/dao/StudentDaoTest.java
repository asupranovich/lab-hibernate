package com.itechart.students.schedule.dao;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.itechart.students.schedule.AbstractTest;
import com.itechart.students.schedule.dao.impl.StudentDaoImpl;
import com.itechart.students.schedule.model.Course;
import com.itechart.students.schedule.model.Student;
import org.junit.Ignore;

@Ignore
public class StudentDaoTest extends AbstractTest {

    private StudentDao studentDao;

    @Override
    public void setup() {
        studentDao = new StudentDaoImpl(em);
    }

    @Test
    public void testGetById() {
        Student student = studentDao.getById(1L);
        Assert.assertNotNull(student);
    }

    @Test
    public void testGetWithAllData() {
        Student studentWithAllData = studentDao.getWithAllData(1L);
        Assert.assertNotNull(studentWithAllData);

        Set<Course> courses = studentWithAllData.getCourses();
        Assert.assertTrue(courses != null && courses.size() > 0);

        Double averageMark = studentWithAllData.getAverageMark();
        Assert.assertTrue(averageMark != null && averageMark > 0);
    }

}
