package org.hibernate.tutorial.test;

import java.io.File;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.PersonWithVersion;

public class Test23_2 {

	/**
	 * Learning Points:
	 * 
	 * Showing StaleObjectStateException 
	 * when 2 concurrent users trying to update same entity and optimistic locking is used. 
	 * another instance is Test23.java
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		SessionFactory sessionFactory = new Configuration().configure(new File("src/hibernate-optmistic-lock.cfg.xml"))
														   .buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
            // load the session object
			PersonWithVersion person = (PersonWithVersion)session.load(PersonWithVersion.class,1);
			person.setName("Setting name from another thread");
			
            // Commit the change
            System.out.println("Before commit: Name = " + person.getName() + ", Version = " + person.getVersion());
            tx.commit();
            session.refresh(person);
            System.out.println("After commit: Name = " + person.getName() + ", Version = " + person.getVersion());

            tx = null;            
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
