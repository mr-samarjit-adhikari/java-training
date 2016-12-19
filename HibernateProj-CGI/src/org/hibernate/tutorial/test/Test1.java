package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Person;

/**
 * Test:
 * Save a Person Object
 * 
 * @author samarjit
 *
 */
public class Test1 {
	public static void main(String[] args)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
			Person person = new Person();
			person.setName("Samarjit Adhikari");
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
	}
}
