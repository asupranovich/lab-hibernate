package com.itechart.students.schedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "ADDRESS")
public class Address extends Identity {

    @Column(name = "KIND")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AddressType type;

    @Column(name = "STREET")
    @NotNull
    @Length.List({
        @Length(min = 3, message = "Street should be at least {min} characters"),
	@Length(max = 50, message = "Street should be less than {max} characters")
    })
    private String street;

    @Column(name = "CITY")
    @NotNull
    @NotEmpty
    private String city;

    @Column(name = "STATE")
    @Length(min = 2, max = 2)
    private String state;

    @Column(name = "ZIP")
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$")
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

    @Override
    public boolean equals(Object obj) {

        if (obj == null || !(obj instanceof Address)) {
            return false;
        }

        Address address = (Address) obj;

        return getCity().equals(address.getCity())
                && getState().equals(address.getState())
                && getStreet().equals(address.getStreet())
                && getZip().equals(address.getZip())
                && getType() == address.getType();
    }

}
