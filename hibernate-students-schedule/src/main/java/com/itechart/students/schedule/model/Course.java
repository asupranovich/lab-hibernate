package com.itechart.students.schedule.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
public class Course extends Identity {

	@Column(name = "NAME")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "STUDENT_COURSE", 
		joinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
	)
	@OrderBy("lastName ASC")
	private List<Student> students = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LECTURER_ID")
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
	private Lecturer lecturer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	
	
}
