package com.itechart.students.schedule.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Student extends Person {

	private List<Course> cources;
	
	@Transient
	private double averageMark;

	public List<Course> getCources() {
		return cources;
	}

	public void setCources(List<Course> cources) {
		this.cources = cources;
	}

	public double getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(double averageMark) {
		this.averageMark = averageMark;
	}
	
	
}
