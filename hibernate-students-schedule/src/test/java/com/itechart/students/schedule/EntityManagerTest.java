package com.itechart.students.schedule;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.itechart.students.schedule.model.Gender;
import com.itechart.students.schedule.model.PersonSimplified;

//@Ignore
public class EntityManagerTest extends AbstractTest {

    @Test
    public void testFind() {
        PersonSimplified person = em.find(PersonSimplified.class, 1L);
        Assert.assertNotNull(person);
        Assert.assertEquals("Cooper", person.getLastName());
    }

    @Test
    public void testPersist() {
        PersonSimplified person = new PersonSimplified();
        person.setFirstName("John");
        person.setLastName("Doe");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1987, 1, 28);
        person.setBirthDate(calendar.getTime());
        person.setGender(Gender.MALE);
        person.setEmail("john.doe@email.tst");
        person.setPhone("+3752999988777");

        executeInTransaction(() -> {
            em.persist(person);
        });

        Long personId = person.getId();
        System.out.println("Student Id is:" + personId);
        Assert.assertTrue(personId > 0);
    }

    @Test
    public void testMerge() {
        PersonSimplified person = em.find(PersonSimplified.class, 16L);
        person.setFirstName("Joan");
        person.setGender(Gender.FEMALE);

        executeInTransaction(() -> {
            em.merge(person);
        });
    }

    @Test
    public void testRefresh() {
        PersonSimplified person = em.find(PersonSimplified.class, 16L);
        Assert.assertEquals("Joan", person.getFirstName());

        // put breakpoint here and change first_name to 'Joanne' in DB
        em.refresh(person);
        Assert.assertEquals("Joanne", person.getFirstName());
    }

    @Test
    public void testAutoFlush() {
        PersonSimplified person = em.find(PersonSimplified.class, 16L);
        person.setLastName("Trump");
        
        // uncomment the next line to show 'detach' behavior
        // em.detach(person);
        executeInTransaction(() -> {});
    }

    @Test
    public void testRemove() {
        PersonSimplified person = em.getReference(PersonSimplified.class, 16L);
        executeInTransaction(() -> {
            em.remove(person);
        });

        PersonSimplified nullPerson = em.find(PersonSimplified.class, 16L);
        Assert.assertNull(nullPerson);
    }

}
