package com.itechart.students.schedule.dao.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.itechart.students.schedule.dao.StudentDao;
import com.itechart.students.schedule.model.Student;

public class StudentDaoImpl extends GenericDaoImpl<Student> implements StudentDao {

	public StudentDaoImpl(EntityManager em) {
		super(em, Student.class);
	}

	@Override
	public Student getWithAllData(Long id) {
		TypedQuery<Student> query = em.createQuery("select s from Student s join fetch courses where s.id = :id",
				Student.class);
		query.setParameter("id", id);
		Student student = query.getSingleResult();
		
		Double averageMark = getAverageMark(id);
		student.setAverageMark(averageMark);

		return query.getSingleResult();
	}

	private Double getAverageMark(Long id) {
		TypedQuery<Double> averageMarkQuery = em.createNamedQuery("getStudentAverageMark", Double.class);
		averageMarkQuery.setParameter(1, id);
		return averageMarkQuery.getSingleResult();
	}
	
	@Override
	public Collection<Student> getAll() {
		TypedQuery<Student> query = em.createQuery("select s from Student s", Student.class);
		return query.getResultList();
	}
}
