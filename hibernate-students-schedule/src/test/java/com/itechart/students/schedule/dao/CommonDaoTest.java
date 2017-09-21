package com.itechart.students.schedule.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itechart.students.schedule.utils.PersistenceUtils;

public class CommonDaoTest {

	protected final static Logger LOG = LoggerFactory.getLogger(CommonDaoTest.class);
	
	protected EntityManager entityManager;

	protected boolean executeInTransaction(WorkUnit workUnit) {
		EntityTransaction transaction = entityManager.getTransaction();
		boolean succefullExecution;
		try {
			transaction.begin();
			workUnit.execute();
			transaction.commit();
			LOG.info("Transaction committed");
			succefullExecution = true;
		} catch (Exception e) {
			LOG.warn("Got exception --> transaction will be rolled back", e);
			transaction.rollback();
			succefullExecution = false;
		}
		return succefullExecution;
	}
	
	public void init() {
		LOG.info("Getting EntityManager ...");
		entityManager = PersistenceUtils.getEntityManager();
	}

	@After
	public void cleanUp() {
		LOG.info("Closing EnityManager");
		entityManager.close();
	}
	
	protected interface WorkUnit {

		void execute();

	}
}
