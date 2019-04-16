package com.itechart.students.schedule.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@NamedNativeQueries({
    @NamedNativeQuery(name = "getStudentAverageMark", query = "SELECT AVG(MARK) FROM STUDENT_COURSE WHERE STUDENT_ID = ?")
})
@Entity
@DiscriminatorValue("S")
public class Student extends Person {

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    @OrderBy("name ASC")
    private Set<Course> courses = new HashSet<>();

    @Column(name = "YEAR")
    private Integer year;

    @Transient
    private Double averageMark;

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }

}
