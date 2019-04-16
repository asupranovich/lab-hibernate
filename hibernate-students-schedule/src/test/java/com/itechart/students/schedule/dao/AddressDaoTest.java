package com.itechart.students.schedule.dao;

import org.junit.Assert;
import org.junit.Test;

import com.itechart.students.schedule.AbstractTest;
import com.itechart.students.schedule.dao.impl.AddressDaoImpl;
import com.itechart.students.schedule.model.Address;
import com.itechart.students.schedule.model.AddressType;
import org.junit.Ignore;

@Ignore
public class AddressDaoTest extends AbstractTest {

    private AddressDao addressDao;

    @Override
    public void setup() {
        addressDao = new AddressDaoImpl(em);
    }

    @Test
    public void testGetById() {
        Address address = addressDao.getById(1L);
        Assert.assertNotNull(address);

        Assert.assertEquals(AddressType.HOME, address.getType());
        Assert.assertEquals("22522", address.getZip());
    }

}
