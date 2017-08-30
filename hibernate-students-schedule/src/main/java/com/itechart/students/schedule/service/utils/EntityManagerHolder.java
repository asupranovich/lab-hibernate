package com.itechart.students.schedule.service.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHolder {

	private static final String PERSISTENT_UNIT = "hello-world-hibernate";

	private static EntityManager entityManager;

	public static synchronized EntityManager getEntityManager() {
		if (entityManager == null) {
			EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
			entityManager = emFactory.createEntityManager();
		}

		return entityManager;
	}

}
