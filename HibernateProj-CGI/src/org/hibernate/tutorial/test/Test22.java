package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.PersonWithVersion;

public class Test22 {

	/**
	 * Learning Points: 
	 * Optimistic Lock: version number kept incrementing every time the Person object is changed and committed.
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

            System.out.println("Before commit: Name = " + person.getName() + ", Version = " + person.getVersion());  // version = 0
            tx.commit();
            session.refresh(person);
            System.out.println("After commit: Name = " + person.getName() + ", Version = " + person.getVersion());     // version = 0

            // 2nd commit
            tx = session.beginTransaction();
            person.setName("Yo man!");
            System.out.println("Before commit: Name = " + person.getName() + ", Version = " + person.getVersion());  // version = 0
            tx.commit();
            session.refresh(person);
            System.out.println("After commit: Name = " + person.getName() + ", Version = " + person.getVersion());     // version = 1

            // 3rd commit
            tx = session.beginTransaction();
            person.setName("Crazy Horse");
            System.out.println("Before commit: Name = " + person.getName() + ", Version = " + person.getVersion());  // version = 1
            tx.commit();
            session.refresh(person);
            System.out.println("After commit: Name = " + person.getName() + ", Version = " + person.getVersion());    // version = 2

            // 4th commit
            tx = session.beginTransaction();
            person.setName("Beautie");
            System.out.println("Before commit: Name = " + person.getName() + ", Version = " + person.getVersion());  // version = 2
            tx.commit();
            session.refresh(person);
            System.out.println("After commit: Name = " + person.getName() + ", Version = " + person.getVersion());  // version = 3
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
