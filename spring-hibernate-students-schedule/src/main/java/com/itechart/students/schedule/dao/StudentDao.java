package com.itechart.students.schedule.dao;

import java.util.Collection;
import com.itechart.students.schedule.model.Student;

public interface StudentDao extends GenericDao<Student> {

    Student getWithAllData(Long id);

    Collection<Student> getAll();

    Collection<Student> search(String keyword);

}
