package com.itechart.students.schedule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itechart.students.schedule.model.Student;

public class CacheTest extends AbstractTest {
	// https://habrahabr.ru/post/135176/
	@Before
	public void init() {
		super.init();
	}
	
	@Test
	public void firstLevelCacheTest() {
		Student student = em.find(Student.class, 5L);
		Assert.assertNotNull(student);
		
		Student student2 = em.find(Student.class, 5L);
		Assert.assertNotNull(student2);
		
		Assert.assertTrue(student == student2);
	}
	
}
