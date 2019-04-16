package com.itechart.students.schedule.dao;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.itechart.students.schedule.AbstractTest;
import com.itechart.students.schedule.dao.impl.CourseDaoImpl;
import com.itechart.students.schedule.model.Course;
import org.junit.Ignore;

@Ignore
public class CourseDaoTest extends AbstractTest {

    private CourseDao courseDao;

    @Override
    public void setup() {
        courseDao = new CourseDaoImpl(em);
    }

    @Test
    public void testGetWithAllData() {
        Course course = courseDao.getWithAllData(1L);
        Assert.assertNotNull(course);
    }

    @Test
    public void testGetAllWithAllData() {
        Collection<Course> courses = courseDao.getAllWithAllData();
        Assert.assertTrue(courses.size() > 0);
    }

    @Test
    public void testGetById() {
        courseDao.getById(1L);
    }

    @Test
    public void testGetAll() {
        courseDao.getAll();
    }
	
}
