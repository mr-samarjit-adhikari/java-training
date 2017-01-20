package org.hibernate.tutorial.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Test32 {

	/**
	 * Learning point;
	 * Named query 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
			
	        // Perform HQL Query with named query.
	        // The "HQLpricing" named query is defined in the "Product"
	        {
	            System.out.println("\n---Performing HQL query with named query...");
	            Query query = session.getNamedQuery("HQLpricing");
	            query.setParameter("price", 25.0);
	            
	            List results = query.list();
	            TestUtils.displayObjectList(results);
	        }			
			
		}
		finally{
			session.close();
		}				

	}

}
