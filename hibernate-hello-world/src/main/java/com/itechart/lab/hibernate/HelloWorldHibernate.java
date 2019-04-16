package com.itechart.lab.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class HelloWorldHibernate {

    private static final String PERSISTENT_UNIT = "hello-world-hibernate";

    public static void main(String a[]) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
        EntityManager em = emf.createEntityManager();

        List<Student> students = getStudents(em);
        PrintUtils.printStudents(students);

        em.close();
        emf.close();
    }

    @SuppressWarnings("unchecked")
    private static List<Student> getStudents(EntityManager em) {
        Query query = em.createQuery("select s from Student s");
        List<Student> students = (List<Student>) query.getResultList();

        return students;
    }

}
