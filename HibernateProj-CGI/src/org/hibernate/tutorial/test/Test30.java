package org.hibernate.tutorial.test;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class Test30 {

	/**
	 * learning point: 
	 * HQL query ..'from' clause
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
	        // Perform HQL query with paging
	        {
	            System.out.println("\n---Performing HQL query with paging...");
				Query<?> query = session.createQuery("from Product");
	            
	            // Set paging requirement
	            query.setFirstResult(1);
	            query.setMaxResults(2);
	            
	            List results = query.list();
	            TestUtils.displayProductsList(results);
	            
	        }
	        
	        // Perform HQL query with like like
	        {
	            System.out.println("\n---Performing HQL query like...");
	            String hql = "from Product where price > 2.0 and name like 'P%'";
	            Query query = session.createQuery(hql);
	            List results = query.list();
	            TestUtils.displayProductsList(results);
	        }
	        
	        // Build a criteria with join
	        {
	            System.out.println("\n---Performing HQL query with join... ");
	            String hql = "from Supplier s inner join fetch s.products as p";
	            Query query = session.createQuery(hql);
	            List results = query.list();
	            TestUtils.displaySupplierList(results);
	        }
			
		}
		finally{
			session.close();
		}					

	}

}
