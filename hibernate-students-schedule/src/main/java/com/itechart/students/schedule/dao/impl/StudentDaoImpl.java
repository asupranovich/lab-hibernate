package com.itechart.students.schedule.dao.impl;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.itechart.students.schedule.dao.StudentDao;
import com.itechart.students.schedule.model.Student;

public class StudentDaoImpl extends GenericDaoImpl<Student> implements StudentDao {

    public StudentDaoImpl(EntityManager em) {
        super(em, Student.class);
    }

    @Override
    public Student getWithAllData(Long id) {
        TypedQuery<Student> query = em.createQuery("select s from Student s join fetch s.courses join fetch s.addresses where s.id = :id",
                Student.class);
        query.setParameter("id", id);
        Student student = query.getSingleResult();

        Double averageMark = getAverageMark(id);
        student.setAverageMark(averageMark);

        return student;
    }

    private Double getAverageMark(Long id) {
        Query averageMarkQuery = em.createNamedQuery("getStudentAverageMark");
        averageMarkQuery.setParameter(1, id);
        BigDecimal averageMark = (BigDecimal) averageMarkQuery.getSingleResult();
        return averageMark.doubleValue();
    }

    @Override
    public Collection<Student> getAll() {
        TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }

    @Override
    public Collection<Student> search(String keyword) {
        return null;
    }

}
