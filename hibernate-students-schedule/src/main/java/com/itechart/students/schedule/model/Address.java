package com.itechart.students.schedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address extends Identity {

/*  In case we use DB sequence */
//	@Id
//	@Column(name = "ADDRESS_ID")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_ID_GEN")
//	@SequenceGenerator(name = "ADDRESS_ID_GEN", sequenceName = "SEQ_ADDRESS_ID", allocationSize = 1, initialValue = 1)
//	private Long id;

	@Column(name = "KIND")
	private AddressType type;
	
	@Column(name = "STREET")
	private String street;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;
	
	@Column(name = "ZIP")
	private String zip;

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
