package com.itechart.students.schedule.service;

import java.util.Collection;

import com.itechart.students.schedule.model.Student;

public interface StudentService {

	Student getWithBasicData(Long id);
	
	Student getWithAllData(Long id);
	
	void delete(Long id);
	
	void saveOrUpdate(Student student);
	
	Collection<Student> getAll();
}
