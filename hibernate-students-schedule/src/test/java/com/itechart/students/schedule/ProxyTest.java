package com.itechart.students.schedule;

import java.util.List;

import org.hibernate.Hibernate;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.itechart.students.schedule.model.Address;
import com.itechart.students.schedule.model.Course;
import com.itechart.students.schedule.model.Lecturer;
import com.itechart.students.schedule.model.Student;

//@Ignore
public class ProxyTest extends AbstractTest {

    @Test
    public void testAddressClass() {
        Address address = em.find(Address.class, 5L);
        Assert.assertNotNull(address);

        Class<?> addressClass = address.getClass();
        System.out.println("Address class is: " + addressClass);
        Assert.assertEquals(Address.class, addressClass);
    }

    @Test
    public void testAddressReferenceClass() {
        Address address = em.getReference(Address.class, 5L);
        Assert.assertNotNull(address);

        Class<?> addressClass = address.getClass();
        System.out.println("Address class is: " + addressClass);
        Assert.assertNotEquals(Address.class, addressClass);

        Object unproxiedAddress = Hibernate.unproxy(address);
        Assert.assertEquals(Address.class, unproxiedAddress.getClass());
    }

    @Test
    public void testLecturerReferenceUsage() {
        executeInTransaction(() -> {
            Lecturer lecturer = em.getReference(Lecturer.class, 11L);
            Course course = em.find(Course.class, 5L);
            course.setLecturer(lecturer);
            em.merge(course);
        });
    }

    @Test
    public void testCourseClass() {
        Course course = em.find(Course.class, 1L);
        Assert.assertNotNull(course);

        Lecturer lecturer = course.getLecturer();
        System.out.println("Lecturer class: " + lecturer.getClass());
        Assert.assertNotNull(lecturer);
        Assert.assertFalse(Hibernate.isInitialized(lecturer));

        List<Student> students = course.getStudents();
        System.out.println("Student list class:" + students.getClass());
        Assert.assertNotNull(students);
        Assert.assertFalse(Hibernate.isInitialized(students));
    }

}
