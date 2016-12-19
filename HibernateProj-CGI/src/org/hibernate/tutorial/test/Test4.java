package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;

/**
 * Tests:
 * merges in different sessions.
 * 
 * Learning Point:
 * Each session represents a different persistence context
 * Persistent objects in a session becomes detached objects after the session it belongs to closes
 * A detached objects still maintains the object identifier that has been assigned before 
 * The difference between transient object and detached object is that the transient object does not have the object identifier (since it has never been assigned before) while detached object does
 * merge() takes a detached object as an argument and then returns a new persistent object that contains the same object identifier that the detached object used to have
 * 
 * @author samarjit
 *
 */
public class Test4 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		Person person  = null;
		Person person2 = null;
		Person person_merged = null;
		
		Object personId1 = null;
		Object personId2 = null;
		
		System.out.println("Session 1 created.");
		try{
			tx = session.beginTransaction();
			
			person = new Person();
			person.setName("Person 1");
			personId1 = session.save(person);
			System.out.println("1st person ID:"+personId1);
			
			/*Creating another person */
			person2 = new Person();// transient object
			person2.setName("Person 2");
			session.save(person2);
			personId2 = session.getIdentifier(person2);
			System.out.println("2nd person ID: "+personId2);
						
			System.out.println("About to commit....");
			tx.commit();
			System.out.println("After Tx commit.");
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
		System.out.println("Session 1 closed. ");
		
		session=sessionFactory.openSession();
		System.out.println("Session 2 Opened. ");
		try{
			tx = session.beginTransaction();
					
			/*Now both person objects are in detached state.Update person and merged */
			person.setName("Person 1 modified");
			
			person_merged = (Person)session.merge(person); //person remains in detached state
			                                               // person_merged is in persistent state
			System.out.println("After merge, person 1 Id is: "+session.getIdentifier(person_merged));
			
			/* Now update person 2 */
			System.out.println("Updating person2..");
			session.update(person2);
			System.out.println("Person 2 Identifier: "+session.getIdentifier(person2));
			
			System.out.println("About to commit....");
			tx.commit();
			System.out.println("After Transaction commit.");
		}
		catch(Exception e){
			if(tx != null){
				System.out.println("Transaction Rollback.");
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		System.out.println("Session 2 closed. ");
	}
}
