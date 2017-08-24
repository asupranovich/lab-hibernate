package com.itechart.students.schedule.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInformation {

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
