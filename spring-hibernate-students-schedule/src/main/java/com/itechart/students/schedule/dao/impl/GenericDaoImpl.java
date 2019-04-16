package com.itechart.students.schedule.dao.impl;

import javax.persistence.EntityManager;

import com.itechart.students.schedule.dao.GenericDao;
import com.itechart.students.schedule.model.Identity;
import javax.persistence.PersistenceContext;

public abstract class GenericDaoImpl<T extends Identity> implements GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;
    private final Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T getById(Long id) {
        T record = em.find(entityClass, id);
        return record;
    }

    @Override
    public Long create(T record) {
        em.persist(record);
        return record.getId();
    }

    @Override
    public void delete(T record) {
        em.remove(record);
    }

    @Override
    public void update(T record) {
        em.merge(record);
    }

    @Override
    public void detach(T record) {
        em.detach(record);
    }

}
