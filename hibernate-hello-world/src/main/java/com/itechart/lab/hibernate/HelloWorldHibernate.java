package com.itechart.lab.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class HelloWorldHibernate {

	public static void main(String a[]) {
		
	}
	
	private void doit() {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("");
		EntityManager em = emFactory.createEntityManager();
		Query query = em.createNativeQuery("SELECT count(*) from APExpressWeb.users");
		query.getSingleResult();
	}
	
}
