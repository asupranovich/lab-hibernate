package com.itechart.students.schedule.dao;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itechart.students.schedule.AbstractTest;
import com.itechart.students.schedule.dao.impl.CourseDaoImpl;
import com.itechart.students.schedule.model.Course;

public class CourseDaoTest extends AbstractTest {

	private CourseDao courseDao;
	
	@Before
	@Override
	public void init() {
		super.init();
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
	
}
