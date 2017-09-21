package com.itechart.students.schedule.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itechart.students.schedule.dao.impl.AddressDaoImpl;
import com.itechart.students.schedule.model.Address;
import com.itechart.students.schedule.model.AddressType;

public class AddressDaoTest extends CommonDaoTest {

	private AddressDao addressDao;
	
	@Before
	public void init() {
		super.init();
		addressDao = new AddressDaoImpl(entityManager);
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
