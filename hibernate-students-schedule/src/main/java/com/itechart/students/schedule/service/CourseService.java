package com.itechart.students.schedule.service;

import com.itechart.students.schedule.model.Course;

public interface CourseService {

	Course getWithBasicData(Long id);
	
	Course getWithAllData(Long id);
	
	void addStudentToCourse(Long courseId, Long studentId);
	
	void delete(Long id);
	
	void saveOrUpdate(Course course);
	
	
}
