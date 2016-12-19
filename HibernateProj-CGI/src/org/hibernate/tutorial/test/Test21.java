package org.hibernate.tutorial.test;



import java.io.File;
import java.sql.Connection;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.tutorial.model.Person;


public class Test21 {

	/**
	 * session per long conversation pattern 
	 * N.B. Disconnected and store in HttpSession
	 * session-per-conversation pattern 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
        // Create SessionFactory and Session object
        SessionFactory sessionFactory = new Configuration().configure(new File("src/hibernate-Session-Per-Conversation.cfg.xml"))
        												   .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.setFlushMode(FlushMode.MANUAL);
        ManagedSessionContext.bind(session);

        Person p1 = null;
        Person p2 = null;
        Person p1_merged = null;

        Object id1 = null;
        Object id2 = null;

        Transaction tx = null;
        try {
            // --- Create first transaction ----
            tx = session.beginTransaction();

            // Create a Person object and save it
            p1 = new Person();  //transient state
            p1.setName("Person 1");
            // Persist the given transient instance, first assigning a 
            // generated identifier.
            session.save(p1);          // persistent state

            id1 = session.getIdentifier(p1);
            System.out.println("Transaction 1, id1 = " + id1);

            // Create another Person object and save it.
            p2 = new Person();  //transient state
            p2.setName("Person 2");
            session.save(p2);          // persistent state

            id2 = session.getIdentifier(p2);
            System.out.println("Transaction 1, id2 = " + id2);

            System.out.println("About to perform commit...");
            tx.commit();
            tx = null; //reset 
            
            System.out.println("After commit...");

            
            // Disconnect the Session from the current JDBC connection.
            // If the connection was obtained by Hibernate close it and return
            // it to the connection pool; otherwise, return it to the application.
            Session savedSession = ManagedSessionContext.unbind(sessionFactory);
            System.out.println("After unbinding the session...");

            System.out.println("Waiting .....");
            // Reconnect to the given JDBC connection. This is used by
            // applications which require long transactions and use
            // application-supplied connections.
            ManagedSessionContext.bind(savedSession);           
            session = savedSession;
            System.out.println("After Rebinding the session...");
            
            // ---- Start the 2nd transaction in the same session  ---
            tx = session.beginTransaction();

            // p1 is still in persistent state since it still
            // has a session it is associated with.
            p1.setName("Person 1 updated");
            // Since p1 is still a persistence object, there is
            // no need to load it from the database table.
            // So you will not see SQL.
            System.out.println("About to perform merge...");
            p1_merged = (Person) session.merge(p1);
            System.out.println("After the merge....");

            id1 = session.getIdentifier(p1_merged);
            System.out.println("Transaction 2, id1 = " + id1);

            // p2 is still in persistent state since it still
            // has a session it is associated with.
            p2.setName("Person 2 updated");
            session.save(p2);
            id2 = session.getIdentifier(p2);
            System.out.println("Transaction 2, id2 = " + id2);

            System.out.println("About to perform commit...flushing session..");
            session.flush();
            tx.commit();
            System.out.println("After commit...");

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } 
        finally {
            session.close();
        }		
	}

}
