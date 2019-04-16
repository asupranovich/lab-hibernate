package com.itechart.students.schedule.dao.impl;

import javax.persistence.EntityManager;

import com.itechart.students.schedule.dao.AddressDao;
import com.itechart.students.schedule.model.Address;

public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {

    public AddressDaoImpl(EntityManager em) {
        super(em, Address.class);
    }

}
