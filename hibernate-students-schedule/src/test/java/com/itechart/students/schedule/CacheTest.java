package com.itechart.students.schedule;

import org.hibernate.Hibernate;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.itechart.students.schedule.model.Course;
import com.itechart.students.schedule.model.Lecturer;

//@Ignore
public class CacheTest extends AbstractTest {

    @Test
    public void firstLevelCacheTest() {
        Lecturer lecturer1 = em.find(Lecturer.class, 10L);
        Assert.assertNotNull(lecturer1);

        Lecturer lecturer2 = em.find(Lecturer.class, 10L);
        Assert.assertNotNull(lecturer2);

        Assert.assertTrue(lecturer1 == lecturer2);

        Course course = em.find(Course.class, 1L);
        Lecturer lecturer3 = course.getLecturer();

// 	uncomment to verify that without pre-loaded lecturer1, lecturer3 won't be initialized
//	Assert.assertFalse(Hibernate.isInitialized(lecturer3));
        Assert.assertTrue(lecturer1 == lecturer3);
    }

}
