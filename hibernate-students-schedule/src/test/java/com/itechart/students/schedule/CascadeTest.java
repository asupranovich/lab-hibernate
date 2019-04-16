package com.itechart.students.schedule;

import java.util.Calendar;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.itechart.students.schedule.model.Address;
import com.itechart.students.schedule.model.AddressType;
import com.itechart.students.schedule.model.ContactInformation;
import com.itechart.students.schedule.model.Gender;
import com.itechart.students.schedule.model.Student;

@Ignore
public class CascadeTest extends AbstractTest {

    @Test
    public void testCascadePersist() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setGender(Gender.MALE);
        student.setYear(4);

        Calendar calendar = Calendar.getInstance();
        calendar.set(1987, 1, 28);
        student.setBirthDate(calendar.getTime());

        ContactInformation contactInfo = new ContactInformation();
        contactInfo.setEmail("john.doe@email.tst");
        contactInfo.setPhone("+3752999988777");
        student.setContactInfo(contactInfo);

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

        student.getAddresses().add(homeAddress);
        student.getAddresses().add(workAddress);

        executeInTransaction(() -> {
            em.persist(student);
        });

        Long studentId = student.getId();
        System.out.println("Student Id is:" + studentId);
    }

    @Test
    public void testCascadeMerge() {
        Student student = em.find(Student.class, 19L);
        student.setGender(Gender.MALE);

        Set<Address> addresses = student.getAddresses();
        Address address = addresses.iterator().next();
        address.setCity("Moscow");
        em.detach(address);

        executeInTransaction(() -> {
            em.merge(student);
        });
    }

    @Test
    public void testOrfanRemoval() {
        Student student = em.find(Student.class, 17L);

        Set<Address> addresses = student.getAddresses();
        Address address = addresses.iterator().next();

        addresses.remove(address);

        executeInTransaction(() -> {
            em.merge(student);
        });
    }

    @Test
    public void testCascadeRemove() {
        Student student = em.getReference(Student.class, 17L);
        executeInTransaction(() -> {
            em.remove(student);
        });

        Student nullStudent = em.find(Student.class, 17L);
        Assert.assertNull(nullStudent);
    }

}
