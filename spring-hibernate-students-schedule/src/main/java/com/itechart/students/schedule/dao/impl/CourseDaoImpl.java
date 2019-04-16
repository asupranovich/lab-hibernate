package com.itechart.students.schedule.dao.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.itechart.students.schedule.dao.CourseDao;
import com.itechart.students.schedule.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl extends GenericDaoImpl<Course> implements CourseDao {

    public CourseDaoImpl() {
        super(Course.class);
    }

    @Override
    public Course getWithAllData(Long id) {
        Course course = em.find(Course.class, 1L);
        return course;
    }

    @Override
    public Collection<Course> getAllWithAllData() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteria = criteriaBuilder.createQuery(Course.class);
        Root<Course> from = criteria.from(Course.class);
        from.fetch("lecturer", JoinType.LEFT);
        criteria.select(from);

        TypedQuery<Course> query = em.createQuery(criteria);

        return query.getResultList();
    }

}
