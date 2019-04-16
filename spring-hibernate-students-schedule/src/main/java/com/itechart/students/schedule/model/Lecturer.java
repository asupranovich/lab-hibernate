package com.itechart.students.schedule.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@DiscriminatorValue("L")
public class Lecturer extends Person {

    @Column(name = "DEGREE")
    private String degree;

    @Column(name = "SALARY")
    private Double salary;

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY)
    @OrderBy("NAME ASC")
    private List<Course> cources = new ArrayList<>();

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Course> getCources() {
        return cources;
    }

    public void setCources(List<Course> cources) {
        this.cources = cources;
    }

}
