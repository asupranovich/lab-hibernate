package com.itechart.students.schedule.dao;

import com.itechart.students.schedule.model.Identity;

public interface GenericDao<T extends Identity> {

    T getById(Long id);

    Long create(T record);

    void delete(T record);

    void update(T record);

    void detach(T record);
}
