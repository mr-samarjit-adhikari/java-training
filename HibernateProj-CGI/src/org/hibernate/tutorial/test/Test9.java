package org.hibernate.tutorial.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.PersonDetails;

public class Test9 {

	/**
	 * Tests:
	 * More annotations with personDetails. 
	 * 
	 * Learning Points: 
	 * 1. Annotations - @Table/@Column
	 * 2. transient object, Temporal objects, @lob : @clob for String datatype and @blob for byte[] datatype
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
			/* Create a new PersonDetails object and save it in Database */
			PersonDetails person = new PersonDetails();
			person.setShortName("Person 1");
			person.setAge(1);
			person.setDob(new Date());
			person.setDescription("This is person 1 description.");
			
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
