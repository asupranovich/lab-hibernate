package com.itechart.students.schedule.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("S")
public class Student extends Person {

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "STUDENT_COURSE", 
		joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
	)
	@OrderBy("NAME ASC")
	private List<Course> courses;

	@Column(name = "YEAR")
	private Integer year;

	@Transient
	private double averageMark;

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public double getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(double averageMark) {
		this.averageMark = averageMark;
	}

}
