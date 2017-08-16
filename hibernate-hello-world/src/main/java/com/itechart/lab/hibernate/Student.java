package com.itechart.lab.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	public Integer id;

	public String firstName;

	public String lastName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
