package com.itechart.lab.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class HelloWorldHibernate {

	private static final String PERSISTENT_UNIT = "hello-world-hibernate";

	public static void main(String a[]) {
		List<Student> students = getStudents();
		PrintUtils.printStudents(students);
		System.exit(0);
	}

	@SuppressWarnings("unchecked")
	private static List<Student> getStudents() {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("select s from Student s");
		List<Student> students = (List<Student>) query.getResultList();
		em.close();

		return students;
	}

	private static EntityManager getEntityManager() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
		return emFactory.createEntityManager();
	}

}
