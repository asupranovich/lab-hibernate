package com.itechart.students.schedule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTest {

    protected final static Logger LOG = LoggerFactory.getLogger(AbstractTest.class);
    private static final String PERSISTENT_UNIT = "lab-students-schedule";

    protected EntityManager em;
    private EntityManagerFactory emf;

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
        em = emf.createEntityManager();
        setup();
    }

    protected void setup() {

    }

    @After
    public void cleanUp() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
    
    protected boolean executeInTransaction(WorkUnit workUnit) {
        EntityTransaction transaction = em.getTransaction();
        boolean commited;
        try {
            transaction.begin();
            workUnit.execute();
            transaction.commit();
            LOG.info("Transaction committed");
            commited = true;
        } catch (Exception e) {
            LOG.warn("Got exception --> transaction will be rolled back", e);
            transaction.rollback();
            commited = false;
        }
        return commited;
    }

    @FunctionalInterface
    protected interface WorkUnit {

        void execute();
        
    }
}
