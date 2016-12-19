package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;

public class Test19 {
	/**
	 * Learning points:
	 * 
	 * Session and transaction scopes:
	 *  session per Transaction --> session has same scope as single database transaction 
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
			person.setName("Person 1");
			session.save(person);
			
			System.out.println("Session 1, id1: "+session.getIdentifier(person));
			
			Person person1 = new Person();
			person1.setName("Person 2");
			session.save(person1);
			
			System.out.println("Session 1, id2: "+session.getIdentifier(person1));			
			
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
