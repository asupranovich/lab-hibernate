package com.itechart.students.schedule.dao.impl;

import com.itechart.students.schedule.dao.AddressDao;
import com.itechart.students.schedule.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {

    public AddressDaoImpl() {
        super(Address.class);
    }

}
