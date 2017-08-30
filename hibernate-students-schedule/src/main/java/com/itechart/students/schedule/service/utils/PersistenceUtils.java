package com.itechart.students.schedule.service.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtils {

	private static final String PERSISTENT_UNIT = "hello-world-hibernate";
	
	private static EntityManager getEntityManager() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
		return emFactory.createEntityManager();
	}
	
}
