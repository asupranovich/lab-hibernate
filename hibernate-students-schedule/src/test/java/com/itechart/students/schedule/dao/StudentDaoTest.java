package com.itechart.students.schedule.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itechart.students.schedule.dao.impl.StudentDaoImpl;
import com.itechart.students.schedule.model.Course;
import com.itechart.students.schedule.model.Student;
import com.itechart.students.schedule.service.utils.PersistenceUtils;

public class StudentDaoTest {

	private EntityManager enityManager;
	private StudentDao studentDao;
	
	@Before
	public void init() {
		System.out.println("Getting EntityManager...");
		enityManager = PersistenceUtils.getEntityManager();
		enityManager.getTransaction();
		studentDao = new StudentDaoImpl(enityManager);
	}
	
	@After
	public void clean() {
		System.out.println("Closing EntityManager...");
		enityManager.close();
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
	
	@Test
	public void testUpdate() {
		EntityTransaction transaction = enityManager.getTransaction();
		transaction.begin();
		Student student = studentDao.getById(1L);
		Assert.assertNotNull(student);
		student.setLastName("Cooper");
		studentDao.update(student);
		transaction.commit();
	}
	
	@Test
	public void testDelete() {
		EntityTransaction transaction = enityManager.getTransaction();
		transaction.begin();
		Student student = studentDao.getById(14L);
		studentDao.delete(student);
//		enityManager.flush();
		transaction.commit();
		
		student = studentDao.getById(14L);
		Assert.assertNull(student);
	}
	
}
