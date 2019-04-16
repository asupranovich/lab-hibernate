package com.itechart.students.schedule;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.itechart.students.schedule.model.Address;
import com.itechart.students.schedule.model.AddressType;
import com.itechart.students.schedule.model.ContactInformation;
import com.itechart.students.schedule.model.Gender;
import com.itechart.students.schedule.model.Student;

//@Ignore
public class EntityManagerTest extends AbstractTest {

    @Test
    public void testFind() {
        Student student = em.find(Student.class, 1L);
        Assert.assertNotNull(student);
        Assert.assertEquals("Cooper", student.getLastName());
        student.getAddresses().size();
    }

    @Test
    public void testPersist() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1987, 1, 28);
        student.setBirthDate(calendar.getTime());

        student.setGender(Gender.MALE);
        student.setYear(4);

        ContactInformation contactInfo = new ContactInformation();
        contactInfo.setEmail("john.doe@email.tst");
        contactInfo.setPhone("+3752999988777");

        student.setContactInfo(contactInfo);

        executeInTransaction(() -> {
            em.persist(student);
        });

        Long studentId = student.getId();
        System.out.println("Student Id is:" + studentId);
        Assert.assertTrue(studentId > 0);
    }

    @Test
    public void testMerge() {
        Student student = em.find(Student.class, 14L);
        student.setGender(Gender.FEMALE);

        executeInTransaction(() -> {
            em.merge(student);
        });
    }

    @Test
    public void testMergeAddresses() {

        Address homeAddress = new Address();
        homeAddress.setCity("Minsk");
        homeAddress.setState("MR");
        homeAddress.setStreet("Lenina 10");
        homeAddress.setType(AddressType.HOME);
        homeAddress.setZip("222000");

        Address workAddress = new Address();
        workAddress.setCity("Minsk");
        workAddress.setState("MR");
        workAddress.setStreet("Tolstogo 10");
        workAddress.setType(AddressType.WORK);
        workAddress.setZip("222000");

        Student student = em.find(Student.class, 15L);
        student.getAddresses().add(homeAddress);
        student.getAddresses().add(workAddress);

        executeInTransaction(() -> {
            em.merge(student);
        });
    }

    @Test
    public void testRefresh() {
        Student student = em.find(Student.class, 15L);
        Assert.assertEquals("John", student.getFirstName());

        // put breakpoint here and change value in DB
        em.refresh(student);
        Assert.assertEquals("John1", student.getFirstName());
    }

    @Test
    public void testAutoFlush() {
        Student student = em.find(Student.class, 9L);
        student.setLastName("Trump111");

//	em.detach(student);
        executeInTransaction(() -> {
        });
    }

    @Test
    public void testRemove() {
        Student student = em.getReference(Student.class, 15L);
        executeInTransaction(() -> {
            em.remove(student);
        });

        Student nullStudent = em.find(Student.class, 15L);
        Assert.assertNull(nullStudent);
    }

}
