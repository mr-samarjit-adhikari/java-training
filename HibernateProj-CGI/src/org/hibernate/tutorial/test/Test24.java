package org.hibernate.tutorial.test;

import javax.swing.JOptionPane;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.PersonWithVersion;

public class Test24 {

	/**
	 * learn pessimistic locking together with Test23-2.java
	 * Run Test24.java first which will do the locking and then run Test23_2.java 
	 * Test23_2.java will face StaleStateObject exception as it already read the object before committing 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
            // Create a Person object and commit it
			PersonWithVersion person = new PersonWithVersion();
            person.setName("Person 1");
            session.save(person);
            tx.commit();
            tx = null;

            // 2nd commit
            tx = session.beginTransaction();
            person.setName("Yo man!");
            System.out.println("Before commit: Name = " + person.getName() + ", Version = " + person.getVersion());  // version = 0
            tx.commit();
            session.refresh(person);
            System.out.println("After commit: Name = " + person.getName() + ", Version = " + person.getVersion());     // version = 1
            tx = null;
            
            //Now load the object with pessimistic locking 
            tx = session.beginTransaction();
            person = (PersonWithVersion)session.load(PersonWithVersion.class, 1,new LockOptions(LockMode.PESSIMISTIC_FORCE_INCREMENT));
            String newName = "";
            newName = JOptionPane.showInputDialog("Please enter your name - you will experience an exception after another user changes the row");
            person.setName(newName);
            
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
