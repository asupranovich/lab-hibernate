package com.itechart.students.schedule.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.itechart.students.schedule.dao.GenericDao;
import com.itechart.students.schedule.model.Identity;

public abstract class GenericDaoImpl<T extends Identity> implements GenericDao<T> {

    protected final EntityManager em;
    private final Class<T> entityClass;

    public GenericDaoImpl(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    @Override
    public T getById(Long id) {
        T record = em.find(entityClass, id);
        return record;
    }

    @Override
    public Long create(T record) {
        executeInTransaction(() -> {
            em.persist(record);
        });
        return record.getId();
    }

    @Override
    public void delete(T record) {
        executeInTransaction(() -> {
            em.remove(record);
        });
    }

    @Override
    public void update(T record) {
        executeInTransaction(() -> {
            em.merge(record);
        });
    }

    @Override
    public void detach(T record) {
        em.detach(record);
    }

    protected boolean executeInTransaction(WorkUnit workUnit) {
        EntityTransaction transaction = em.getTransaction();
        boolean commited;
        try {
            transaction.begin();
            workUnit.execute();
            transaction.commit();
            commited = true;
        } catch (Exception e) {
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
