package com.itechart.students.schedule.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itechart.students.schedule.dao.impl.StudentDaoImpl;
import com.itechart.students.schedule.model.Course;
import com.itechart.students.schedule.model.Student;
import com.itechart.students.schedule.service.utils.EntityManagerHolder;

public class StudentDaoTest {

	private StudentDao studentDao;
	
	@Before
	public void init() {
		EntityManager em = EntityManagerHolder.getEntityManager();
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
		
		List<Course> courses = studentWithAllData.getCourses();
		Assert.assertTrue(courses != null && courses.size() > 0);
		
		Double averageMark = studentWithAllData.getAverageMark();
		Assert.assertTrue(averageMark != null && averageMark > 0);
	}
	
}
