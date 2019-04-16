package com.itechart.students.schedule.dao;

import java.util.Collection;

import com.itechart.students.schedule.model.Course;

public interface CourseDao extends GenericDao<Course> {

    Course getWithAllData(Long id);

    Collection<Course> getAllWithAllData();
}
