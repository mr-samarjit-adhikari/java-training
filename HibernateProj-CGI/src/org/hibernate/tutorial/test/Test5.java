package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;

/**
 * Tests:
 * merges in different sessions
 * save() the person2 object rather than update()
 * save() will create another identifier for detached objects and copy rest of the fields.
 * 
 * @author samarjit
 *
 */
public class Test5 {

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
			
			/* Now save person 2 blindly */
			System.out.println("Saving(not updating) person2..");
			session.save(person2);
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
