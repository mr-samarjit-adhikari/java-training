package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;


/**
 * Tests:
 * Transient objects do not get committed to the database table
 * Only persistent objects do get committed to the database table
 * 
 * @author samarjit
 *
 */
public class Test3 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
			Person person = new Person();
			person.setName("Samarjit Adhikari");
			Object personId = session.save(person);
			System.out.println("1st person ID:"+personId);
						
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
		
		session=sessionFactory.openSession();
		
		try{
			tx = session.beginTransaction();
					
			/*Creating another person */
			Person person2 = new Person();// transient object
			person2.setName("Person 2");
			//session.save(person2);
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
		
	}
}
