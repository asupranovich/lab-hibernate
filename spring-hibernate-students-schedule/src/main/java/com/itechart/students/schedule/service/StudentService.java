package com.itechart.students.schedule.service;

import com.itechart.students.schedule.model.Student;

/**
 *
 * @author asupranovich
 */
public interface StudentService {

    Student getById(long id);
    
    Student getWithAllDataById(long id);
    
    void save(Student student);
    
}
