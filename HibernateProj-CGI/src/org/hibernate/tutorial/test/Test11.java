package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Address;
import org.hibernate.tutorial.model.PersonDetails;

/**
 * Learning Point: 
 * @AttributeOverrides annotations
 * 
 * @author adhikari
 *
 */
public class Test11 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
					
			Address  homeAddr = new Address();
			homeAddr.setCity("Bangalore");
			homeAddr.setStreets("Whitefield Road");
			homeAddr.setState("karnataka");
			homeAddr.setPincode(560016);
			
			PersonDetails person = new PersonDetails();			
			person.setShortName("Person 1");
			person.setAddress(homeAddr);

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
