package com.itechart.students.schedule;


import java.util.Date;

import javax.persistence.EntityManager;

import com.itechart.students.schedule.model.ContactInformation;
import com.itechart.students.schedule.model.Gender;
import com.itechart.students.schedule.model.Student;
import com.itechart.students.schedule.service.StudentService;
import com.itechart.students.schedule.service.impl.StudentServiceImpl;
import com.itechart.students.schedule.service.utils.PersistenceUtils;

public class Runner {


	public static void main(String a[]) {
		EntityManager entityManager = PersistenceUtils.getEntityManager();
		StudentService studentService = new StudentServiceImpl(entityManager);
		Student student = new Student();
		student.setFirstName("Andy");
		student.setLastName("MiniCooper");
		student.setGender(Gender.MALE);
		student.setBirthDate(new Date());
		
		ContactInformation contactInfo = new ContactInformation();
		contactInfo.setEmail("blabla@email.com");
		contactInfo.setPhone("+365297771117");
		student.setContactInfo(contactInfo);
		
		studentService.saveOrUpdate(student);
		entityManager.close();
		System.exit(0);
	}

	
	
}
