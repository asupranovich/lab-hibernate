package com.itechart.students.schedule.service.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.itechart.students.schedule.dao.StudentDao;
import com.itechart.students.schedule.dao.impl.StudentDaoImpl;
import com.itechart.students.schedule.model.Student;
import com.itechart.students.schedule.service.StudentService;
import com.itechart.students.schedule.service.utils.EntityManagerHolder;
import com.itechart.students.schedule.service.utils.PersistenceUtils;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	private EntityManager entityManager;
	
	public StudentServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.studentDao = new StudentDaoImpl(entityManager);
	}
	
	@Override
	public Student getWithBasicData(Long id) {
		return studentDao.getById(id);
	}

	@Override
	public Student getWithAllData(Long id) {
		return studentDao.getWithAllData(id);
	}

	@Override
	public void delete(Long id) {
		Student student = studentDao.getById(id);
		if(student != null) {
			studentDao.delete(student);
		}
	}

	@Override
	public void saveOrUpdate(Student student) {
		EntityTransaction transaction = entityManager.getTransaction();
		Long id = student.getId();
		transaction.begin();
		if(id == null) {
			studentDao.create(student);
		} else {
			studentDao.update(student);
		}
		transaction.commit();
	}
	
	@Override
	public Collection<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
