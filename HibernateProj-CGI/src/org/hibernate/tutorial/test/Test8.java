package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;

public class Test8 {

	/**
	 * Tests:
	 * Difference between get()  and load() call
	 * 
	 * Learning Point: 
	 * 1. load() will return the object from cache (1st Level) if found, else will return a proxy object. 
	 * 2. load() will result a DB select query only when a non-primary member of the object was accessed. 
	 * 3. load() returns OBJECT_NOT_FOUND if the object does not exists in DB
	 * 4. get() will always resulted a DB select query 
	 * 5. get() returns null if the object was not found in Database.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
			/* Create a new Person object and save it in Database */
			Person person = new Person();
			person.setName("Person 1");
			session.save(person);
			
			tx.commit();
		}
		catch(Exception e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
		session = sessionFactory.openSession();
		/* Now retrieve the data using get() in different session. */
		try{
			System.out.println("Retrieving data using get()");
			tx = session.beginTransaction();
			
			/* Retrieve the data from Database */
			Person person = session.get(Person.class, 1);
			System.out.println("Retrieved person using get(): Name - "+person.getName());
			
			Person person2 = session.get(Person.class, 2); //Using a non existence ID
			System.out.println("Retrieved person2 using get(): value - "+person2);			
			
			tx.commit();
		}
		catch(Exception e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}		
		
		session = sessionFactory.openSession();
		/* Now retrieve the data using load() in different session. */
		try{
			System.out.println("Retrieving data using load()");
			tx = session.beginTransaction();
			
			/* Retrieve the data from Database */
			Person person = session.load(Person.class, 1);
			System.out.println("After load() person class is:"+person.getClass());
			System.out.println("Retrieved person using load(): Name - "+person.getName());
					
			tx.commit();
		}
		catch(Exception e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
		session = sessionFactory.openSession();
		/* Now retrieve non existence data using load() in different session. */
		try{
			System.out.println("Retrieving non existence data using load()");
			tx = session.beginTransaction();
			
			/* Retrieve the data from Database */		
			Person person2 = session.load(Person.class, 2); //Using a non existent ID
			System.out.println("Retrieved person2 using get(): Name - "+person2.getName());			
			
			tx.commit();
		}
		catch(Exception e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}						

	}

}
