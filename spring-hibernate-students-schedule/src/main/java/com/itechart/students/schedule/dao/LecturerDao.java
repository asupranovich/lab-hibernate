package com.itechart.students.schedule.dao;

import java.util.Collection;
import com.itechart.students.schedule.model.Lecturer;

public interface LecturerDao extends GenericDao<Lecturer> {

    Lecturer getWithAllData(Long id);

    Collection<Lecturer> getAll();
}
