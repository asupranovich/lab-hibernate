package com.itechart.students.schedule;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.itechart.students.schedule.model.Student;

public class Runner {

	private static final String PERSISTENT_UNIT = "hello-world-hibernate";

	public static void main(String a[]) {
		List<Student> students = getStudents();
		System.exit(0);
	}

	@SuppressWarnings("unchecked")
	private static List<Student> getStudents() {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("select s from Student s join fetch s.courses");
		List<Student> students = (List<Student>) query.getResultList();
		em.close();

		return students;
	}

	private static EntityManager getEntityManager() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
		return emFactory.createEntityManager();
	}
	
}
