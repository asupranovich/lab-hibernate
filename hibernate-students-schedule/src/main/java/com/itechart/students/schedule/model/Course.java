package com.itechart.students.schedule.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "COURSE")
public class Course extends Identity {

	@Column(name = "NAME")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
	@OrderBy("LAST_NAME ASC")
	private List<Student> students;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LECTURER_ID")
	@Fetch(FetchMode.SELECT)
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
