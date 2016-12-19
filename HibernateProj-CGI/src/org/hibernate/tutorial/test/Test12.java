package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Address;
import org.hibernate.tutorial.model.UserDetails;


/**
 * Learning Point:
 * Saving collection of embeddable objects which will be saved in seperate table 
 *   
 * @author Samarjit
 *
 */
public class Test12 {

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
			
			UserDetails user = new UserDetails();			
			user.setName("User 1");
			user.getAddressList().add(homeAddr);

			session.save(user);						
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
