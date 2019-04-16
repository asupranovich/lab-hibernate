package com.itechart.students.schedule;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.itechart.students.schedule.model.Gender;
import com.itechart.students.schedule.model.Person;
import com.itechart.students.schedule.model.Student;

//@Ignore
public class QueriesTest extends AbstractTest {

    @Test
    public void testJpqQuery() {
        TypedQuery<Person> personQuery = em.createQuery("select p from Person p join p.addresses a where p.gender = :gender and a.city = :city order by p.lastName", Person.class);
        personQuery.setFirstResult(0);
        personQuery.setMaxResults(10);

        personQuery.setParameter("gender", Gender.MALE);
        personQuery.setParameter("city", "Mankato");

        List<Person> persons = personQuery.getResultList();
        Assert.assertEquals(3, persons.size());

        persons.forEach((person) -> {
            System.out.println(person.getClass());
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSqlQuery() {
        Query personQuery = em.createNativeQuery("SELECT p.* FROM PERSON p INNER JOIN ADDRESS a ON p.ID = a.OWNER_ID WHERE p.GENDER = :gender AND a.CITY = :city ORDER BY p.LAST_NAME", Person.class);
        personQuery.setFirstResult(0);
        personQuery.setMaxResults(10);

        personQuery.setParameter("gender", Gender.MALE.name());
        personQuery.setParameter("city", "Mankato");

        List<Person> persons = (List<Person>) personQuery.getResultList();
        Assert.assertEquals(3, persons.size());
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

    @Test
    public void testJPACriteriaQuery() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = criteriaBuilder.createQuery(Person.class);
        Root<Person> person = criteria.from(Person.class);
        Join<Object, Object> address = person.join("addresses");

        Predicate genderCondition = criteriaBuilder.equal(person.get("gender"), Gender.MALE);
        Predicate cityCondition = criteriaBuilder.equal(address.get("city"), "Mankato");
        Predicate conditions = criteriaBuilder.and(genderCondition, cityCondition);

        Order order = criteriaBuilder.asc(person.get("lastName"));

        criteria.select(person).where(conditions).orderBy(order);

        TypedQuery<Person> personQuery = em.createQuery(criteria);
        personQuery.setFirstResult(0);
        personQuery.setMaxResults(10);

        List<Person> persons = (List<Person>) personQuery.getResultList();
        Assert.assertEquals(3, persons.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testHibernateCriteriaQuery() {
        Session session = em.unwrap(Session.class);
        Criteria personCriteria = session.createCriteria(Person.class);
        personCriteria.createCriteria("addresses", "address").add(Restrictions.eq("city", "Mankato"));
        personCriteria.add(Restrictions.eq("gender", Gender.MALE));
        personCriteria.setFirstResult(0);
        personCriteria.setMaxResults(10);
        personCriteria.addOrder(org.hibernate.criterion.Order.asc("firstName"));

        List<Person> persons = (List<Person>) personCriteria.list();
        Assert.assertEquals(3, persons.size());
    }

}
