package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;

/**
 * Tests:
 * tx.rollback() on persistent objects.
 * 
 * Learning Point:
 * Look into DB Queries
 * 
 * @author samarjit
 *
 */
public class Test7 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		Person person  = null;
		
		Object personId1 = null;
		
		System.out.println("Session 1 created.");
		try{
			tx = session.beginTransaction();
			
			person = new Person();
			person.setName("Person 1");
			personId1 = session.save(person);
			System.out.println("1st person ID:"+personId1+" Name:"+person.getName());
			
			System.out.println("About to commit....");
			tx.commit();
			tx=null;
			System.out.println("After Tx commit.");			
			
			tx = session.beginTransaction();
			/* Modify the persistent object */
			person.setName("Person 1 modified");
			System.out.println("Person name before rollback: "+person.getName());
			
			tx.rollback();
			
			System.out.println("Person name after rollback: "+person.getName());

		}
		finally{
			session.close();
		}
		System.out.println("Session 1 closed. ");
	}
}
