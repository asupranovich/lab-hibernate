package com.itechart.students.schedule;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itechart.students.schedule.utils.PersistenceUtils;

public abstract class AbstractTest {

	protected final static Logger LOG = LoggerFactory.getLogger(AbstractTest.class);
	
	protected EntityManager em;

	protected boolean executeInTransaction(WorkUnit workUnit) {
		EntityTransaction transaction = em.getTransaction();
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
		em = PersistenceUtils.getEntityManager();
	}

	@After
	public void cleanUp() {
		LOG.info("Closing EnityManager");
		if(em.isOpen()) {
			em.close();
		}
	}
	
	protected interface WorkUnit {

		void execute();

	}
}
