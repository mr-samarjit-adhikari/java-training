package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Address;
import org.hibernate.tutorial.model.PersonDetails;

/*
 * Learning Points:
 * ValueType Objects. @Embeddable, @Embedded annotations
 */
public class Test10 {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
					
			Address  addr = new Address();
			addr.setCity("Bangalore");
			addr.setStreets("Whitefield Road");
			addr.setState("karnataka");
			addr.setPincode(560016);
			
			PersonDetails person = new PersonDetails();			
			person.setShortName("Person 1");
			person.setAddress(addr);

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
