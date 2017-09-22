package com.itechart.students.schedule.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itechart.students.schedule.AbstractTest;
import com.itechart.students.schedule.dao.impl.AddressDaoImpl;
import com.itechart.students.schedule.dao.impl.StudentDaoImpl;
import com.itechart.students.schedule.model.Address;
import com.itechart.students.schedule.model.AddressType;

public class AddressDaoTest extends AbstractTest {

	private AddressDao addressDao;
	private StudentDao studentDao;
	
	@Before
	public void init() {
		super.init();
		addressDao = new AddressDaoImpl(em);
		studentDao = new StudentDaoImpl(em);
	}
	
	@Test
	public void testGetById() {
		Address address = addressDao.getById(1L);
		Assert.assertNotNull(address);
		
		Assert.assertEquals(AddressType.HOME, address.getType());
		Assert.assertEquals("22522", address.getZip());
	}
	
	@Test
	public void testCreate() {
		
		Address address = new Address();
		address.setCity("Minsk");
		address.setType(AddressType.WORK);
		address.setState("MR");
		address.setZip("222000");
		address.setStreet("Tolstogo, 10");
		
		executeInTransaction(new WorkUnit() {
			@Override
			public void execute() {
				addressDao.create(address);				
			}
		});
		
		Address createdAddress = addressDao.getById(address.getId());
		Assert.assertEquals(address, createdAddress);
	}
	
	
	
}
