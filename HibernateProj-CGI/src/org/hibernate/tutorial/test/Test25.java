package org.hibernate.tutorial.test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tutorial.model.Product;
import org.hibernate.tutorial.model.Supplier;

public class Test25 {

	/**
	 * Learning point: 
	 * criteria with restriction simple demo 
	 * 
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
			
			{
				System.out.println("\n---Using criteria with no restriction...");				
				Transaction tx = session.beginTransaction();						
				Criteria criteria = session.createCriteria(Product.class);
				List<Product> products =  criteria.list();
				TestUtils.displayProductsList(products);
				
				tx.commit();
				tx=null;
			}
			
			{
				System.out.println("\n---Using criteria with paging...");				
				Transaction tx = session.beginTransaction();				
				Criteria criteria = session.createCriteria(Product.class);
				
				criteria.setFirstResult(1);
				criteria.setMaxResults(2);
				
				List<Product> products =  criteria.list();
				TestUtils.displayProductsList(products);
				
				tx.commit();
				tx=null;
			}
			
			{
				System.out.println("\n---Using criteria with two Restrictions...");				
				Transaction tx = session.beginTransaction();						
				Criteria criteria = session.createCriteria(Product.class);
				criteria.add(Restrictions.gt("price", new Double(25.0)));
				criteria.add(Restrictions.like("name", "P%"));
				
				List<Product> products =  criteria.list();
				TestUtils.displayProductsList(products);
				
				tx.commit();
				tx=null;
			}
			
			{
				System.out.println("\n---Using criteria with Restrictions.or... ");				
				Transaction tx = session.beginTransaction();						
				Criteria criteria = session.createCriteria(Product.class);
				criteria.add(Restrictions.or(
								Restrictions.gt("price", 25.0),
								Restrictions.like("name", "P%")
								)
							);
				List<Product> products =  criteria.list();
				TestUtils.displayProductsList(products);
				
				tx.commit();
				tx=null;
			}
			
	        // Build a criteria with Order
	        {
	            System.out.println("\n---Using criteria with Order... ");
	            Criteria crit = session.createCriteria(Product.class);
	            crit.add(Restrictions.gt("price",new Double(1.0)));
	            crit.addOrder(Order.desc("price"));
	            
	            List<Product> results = crit.list();
	            TestUtils.displayProductsList(results);
	            
	        }
	        
	        // Build a criteria with Association and Order
	        // Retrieve all suppliers who has products any of whose price is over 25.0.
	        {
	            System.out.println("\n---Using criteria with Association and Order... ");
	            Criteria crit = session.createCriteria(Supplier.class);
	            crit.addOrder(Order.desc("name"));
	            
	            Criteria prdCrit = crit.createCriteria("products");
	            prdCrit.add(Restrictions.gt("price",new Double(25.0)));
	            
	            List<Product> results = prdCrit.list();
	            TestUtils.displaySupplierList(results);
	            
	        }			
			
		}
		finally{
			session.close();
		}

	}

}
