package com.itechart.students.schedule.dao;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itechart.students.schedule.AbstractTest;
import com.itechart.students.schedule.dao.impl.StudentDaoImpl;
import com.itechart.students.schedule.model.Course;
import com.itechart.students.schedule.model.Student;

public class StudentDaoTest extends AbstractTest {

	private StudentDao studentDao;

	@Before
	public void init() {
		super.init();
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

	@Test
	public void testUpdate() {

		executeInTransaction(new WorkUnit() {
			@Override
			public void execute() {
				Student student = studentDao.getById(1L);
				Assert.assertNotNull(student);

				student.setLastName("Cooper111");
				studentDao.update(student);
			}
		});
	}

	@Test
	public void testDelete() {

		executeInTransaction(new WorkUnit() {
			@Override
			public void execute() {
				Student student = studentDao.getById(14L);
				studentDao.delete(student);
			}
		});
		
		Student student = studentDao.getById(14L);
		Assert.assertNull(student);
	}

}
