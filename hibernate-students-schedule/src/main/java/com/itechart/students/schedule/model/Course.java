package com.itechart.students.schedule.model;

import java.util.List;

import javax.persistence.Id;

public class Course {

	@Id
	private Long id;
	
	private String name;
	
	private List<Student> students;
	
	private Lecturer lecturer;
}
