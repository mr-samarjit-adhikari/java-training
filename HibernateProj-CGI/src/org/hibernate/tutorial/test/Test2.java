package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;

/**
 * Tests:
 * When a domain object gets created, it is in transient state
 * save() method makes a transient object into persistent object
 * save() method assigns object identifier and returns it as return value
 * Actual SQL requests are sent when persistent objects in a session get committed
 * 
 * @author samarjit
 *
 */
public class Test2 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
			Person person = new Person();
			person.setName("Samarjit Adhikari");
			Object personId = session.save(person);
			System.out.println("person ID in 1st Save:"+personId);
			
			/*Creating another person */
			Person person2 = new Person();
			person2.setName("Person 2");
			session.save(person2);
			System.out.println("Person 2 Identifier: "+session.getIdentifier(person2));
			
			System.out.println("About to commit....");
			tx.commit();
			System.out.println("After Transaction commit.");
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
