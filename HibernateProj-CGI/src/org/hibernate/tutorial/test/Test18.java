package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;


public class Test18 {
/**
 * Learning points:
 * 
 * Session and transaction scopes- 
 *  session per operation --> session has same scope as single database operation 
 *  
 * @param args
 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			System.out.println("Session 1 is created: ");
			
			Person person = new Person();
			person.setName("Person with Tx 1");
			session.save(person);
			
			System.out.println("Session 1, id1: "+session.getIdentifier(person));
			
			tx.commit();
			tx=null;
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
		session = sessionFactory.openSession();
		System.out.println("Session 2 is created: ");
		
		try{
			tx = session.beginTransaction();
			
			Person person = new Person();
			person.setName("Person with Tx 2");
			session.save(person);
			
			System.out.println("Session 2, id2: "+session.getIdentifier(person));
			
			tx.commit();
			tx=null;
		}
		catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}

	}

}
