package com.itechart.students.schedule;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.itechart.students.schedule.model.Course;
import com.itechart.students.schedule.model.Lecturer;
import com.itechart.students.schedule.model.Student;

//@Ignore
public class FetchTest extends AbstractTest {

    @Test
    public void testLazyLoading() {
        Course course = em.find(Course.class, 1L);
        Assert.assertNotNull(course);

        List<Student> students = course.getStudents();
        Assert.assertNotNull(students);
        Assert.assertFalse(Hibernate.isInitialized(students));

        Lecturer lecturer = course.getLecturer();
        Assert.assertNotNull(lecturer);
        Assert.assertFalse(Hibernate.isInitialized(lecturer));

        System.out.println("Number of students:");
        System.out.println(students.size());

        System.out.println("Lecturer name:");
        System.out.println(lecturer.getFirstName() + " " + lecturer.getLastName());
    }

    @Test
    // TODO: change FetchType in Course class to EAGER before running
    public void testEagerLoading() {
        Course course = em.find(Course.class, 1L);
        Assert.assertNotNull(course);

        List<Student> students = course.getStudents();
        Assert.assertNotNull(students);
        Assert.assertTrue(Hibernate.isInitialized(students));

        Lecturer lecturer = course.getLecturer();
        Assert.assertNotNull(students);
        Assert.assertTrue(Hibernate.isInitialized(lecturer));

        System.out.println("Number of students:");
        System.out.println(students.size());

        System.out.println("Lecturer name:");
        System.out.println(lecturer.getFirstName() + " " + lecturer.getLastName());
    }

    @Test
    // TODO: change FetchType in Course class back to LAZY before running
    public void testJoinFetch() {
        TypedQuery<Course> courseQuery = em.createQuery("select c from Course c join fetch c.students join fetch c.lecturer where c.id = :courseId", Course.class);
        courseQuery.setParameter("courseId", 1L);

        Course course = courseQuery.getSingleResult();
        Assert.assertNotNull(course);

        List<Student> students = course.getStudents();
        Assert.assertNotNull(students);
        Assert.assertTrue(Hibernate.isInitialized(students));

        Lecturer lecturer = course.getLecturer();
        Assert.assertNotNull(students);
        Assert.assertTrue(Hibernate.isInitialized(lecturer));

        System.out.println("Number of students:");
        System.out.println(students.size());

        System.out.println("Lecturer name:");
        System.out.println(lecturer.getFirstName() + " " + lecturer.getLastName());
    }

    @Test(expected = LazyInitializationException.class)
    public void testLazyInitializationException() {
        Course course = em.find(Course.class, 1L);
        Assert.assertNotNull(course);

        em.close();

        Lecturer lecturer = course.getLecturer();
        Assert.assertNotNull(lecturer);
        Assert.assertFalse(Hibernate.isInitialized(lecturer));

        lecturer.getFirstName();
    }

    @Test
    public void testNPlus1() {
        TypedQuery<Course> courseQuery = em.createQuery("select c from Course c", Course.class);
        List<Course> courses = courseQuery.getResultList();
        Assert.assertTrue(courses != null && courses.size() > 0);

        for (Course course : courses) {
            System.out.println(course.getStudents().size());
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    //TODO: uncomment Fetch annotation in Course class before running
    public void testFetchMode() {
        // FetchMode.JOIN works only with session
        Session session = em.unwrap(Session.class);
        Criteria courseCriteria = session.createCriteria(Course.class);
        List<Course> courses = (List<Course>) courseCriteria.list();

        Assert.assertTrue(courses != null && courses.size() > 0);

        for (Course course : courses) {
            System.out.println(course.getStudents().size());
        }
    }

}
