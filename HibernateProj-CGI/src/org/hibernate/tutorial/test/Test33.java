package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Product;

public class Test33 {

	/**
	 * Hibernate Caching 
	 * first level(session) cache demo 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
			
			Transaction tx = session.beginTransaction();
	        Product product = null;

	        System.out.println("----> Perform a query 1st time");
	        product = session.createQuery("from Product where name='Product 1'",Product.class).getSingleResult();  

	        System.out.println("----> Read it first time after query");
	        product = (Product) session.get(Product.class, product.getId());

	        System.out.println("----> Read it second time after query");
	        product = (Product) session.get(Product.class, product.getId());

	        boolean b = session.contains(product);
	        System.out.println("----> session.contains(product) = " + b);

	        System.out.println("----> Evict it from session cache");
	        session.evict(product);

	        b = session.contains(product);
	        System.out.println("----> session.contains(product) = " + b);

	        System.out.println("----> Read it first time after eviction");
	        product = (Product) session.get(Product.class, product.getId());

	        System.out.println("----> Read it again");
	        product = (Product) session.get(Product.class, product.getId());

	        tx.commit();

	        tx = session.beginTransaction();
	        System.out.println("----> Read it again in a new transaction");
	        product = (Product) session.get(Product.class, product.getId());
	        tx.commit();			

		}
		finally{
			session.close();
		}
	}

}
