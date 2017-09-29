package com.itechart.students.schedule;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.itechart.students.schedule.model.Gender;
import com.itechart.students.schedule.model.Student;

public class QueriesTest extends AbstractTest {

	@Test
	public void testJpqQuery() {
		TypedQuery<Student> studentQuery = em.createQuery("select s from Student s where gender = :gender", Student.class);
		studentQuery.setFirstResult(0);
		studentQuery.setMaxResults(3);
		studentQuery.setParameter("gender", Gender.FEMALE);
		
		List<Student> students = studentQuery.getResultList();
		Assert.assertEquals(3, students.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSqlQuery() {
		Query studentQuery = em.createNativeQuery("SELECT * FROM test3.PERSON WHERE PERSON_TYPE = 'S' AND GENDER = :gender LIMIT 3", Student.class);
		studentQuery.setParameter("gender", Gender.MALE.name());
		List<Student> students = (List<Student>) studentQuery.getResultList();
		Assert.assertEquals(3, students.size());
	}
	
	@Test
	public void testNamedQuery() {
		TypedQuery<Student> studentQuery = em.createNamedQuery("getStudentWithAllData", Student.class);
		studentQuery.setParameter("id", 3L);
		Student student = studentQuery.getSingleResult();
		Assert.assertNotNull(student);
		Assert.assertTrue(student.getCourses().size() > 0);
		Assert.assertTrue(student.getAddresses().size() > 0);
	}
	
	@Test
	public void testNamedSqlQuery() {
		Query averageMarkQuery = em.createNamedQuery("getStudentAverageMark");
		averageMarkQuery.setParameter(1, 3L);
		BigDecimal averageMark = (BigDecimal) averageMarkQuery.getSingleResult();
		Assert.assertTrue(averageMark.doubleValue() > 0);
	}
	
	public void testJPACriteriaQuery() {
		
	}
	
	public void testHibernateCriteriaQuery() {
		
	}
	
}
