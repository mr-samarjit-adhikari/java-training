package org.hibernate.tutorial.test;

import java.util.List;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Product;
import org.hibernate.tutorial.model.Supplier;

public class Test28 {

	/**
	 * Learning point :
	 * HibernateCriteriaQuery with FetchMode specified 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
			//session.clear(); //clear the 1st level cache 
						
	        // Build a criteria with Association with FetchMode.SELECT
	        {
	            System.out.println("\n---Using criteria with Association with FetchMode.SELECT... ");
	            Criteria crit = session.createCriteria(Supplier.class);
	            
	            crit.setFetchMode("products", FetchMode.SELECT);
	            
	            List results = crit.list();
	            TestUtils.displaySupplierList(results);
	            
//	            Supplier supplier0 = (Supplier)results.get(0);
//	            TestUtils.displayProductsList(supplier0.getProducts());	            
	        }
	        
		    // Build a criteria with Association with FetchMode.JOIN
	        {
	            System.out.println("\n---Using criteria with Association with FetchMode.JOIN... ");
	            Criteria crit = session.createCriteria(Supplier.class);
	            
	            crit.setFetchMode("products", FetchMode.JOIN);
	            
	            List results = crit.list();
	            TestUtils.displaySupplierList(results);
	            
	        }        
	        
	        // Build a criteria with Association with FetchMode.DEFAULT
	        {
	            System.out.println("\n---Using criteria with Association with FetchMode.DEFAULT... ");
	            Criteria crit = session.createCriteria(Supplier.class);
	            
	            crit.setFetchMode("products", FetchMode.DEFAULT);
	            
	            List results = crit.list();
	            TestUtils.displaySupplierList(results);
	            
	        }
	    			
		}
		finally{
			session.close();
		}

	}

}
