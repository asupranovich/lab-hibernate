package com.itechart.students.schedule.service.impl;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.itechart.students.schedule.dao.StudentDao;
import com.itechart.students.schedule.dao.impl.StudentDaoImpl;
import com.itechart.students.schedule.model.Student;
import com.itechart.students.schedule.service.StudentService;
import com.itechart.students.schedule.service.utils.EntityManagerHolder;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	
	public StudentServiceImpl() {
		EntityManager entityManager = EntityManagerHolder.getEntityManager();
		studentDao = new StudentDaoImpl(entityManager);
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
		Long id = student.getId();
		if(id == null) {
			studentDao.create(student);
		} else {
			studentDao.update(student);
		}
	}
	
	@Override
	public Collection<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
