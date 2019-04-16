package com.itechart.students.schedule.dao.impl;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.itechart.students.schedule.dao.LecturerDao;
import com.itechart.students.schedule.model.Lecturer;

public class LecturerDaoImpl extends GenericDaoImpl<Lecturer> implements LecturerDao {

    public LecturerDaoImpl(EntityManager em) {
        super(em, Lecturer.class);
    }

    @Override
    public Lecturer getWithAllData(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Lecturer> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
