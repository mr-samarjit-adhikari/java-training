package org.hibernate.tutorial.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Test31 {

	public static void main(String[] args) {
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
			
	        {
	            System.out.println("\n---select p.price from Product p where price > 2.0 and name like 'P%'");
	            String hql = "select p.price from Product p where price > 2.0 and name like 'P%'";
	            Query query = session.createQuery(hql);
	            List results = query.list();
	            TestUtils.displayPriceList(results);
	        }
	        
	        {
	            System.out.println("\n---select p.supplier.name from Product p where price > 2.0 and name like 'P%'");
	            String hql = "select p.supplier.name from Product p where price > 2.0 and name like 'P%'";
	            Query query = session.createQuery(hql);
	            List results = query.list();
	            TestUtils.displaySupplierNameList(results);
	        }
	        
	        //parameter substitution 
	        {
	            System.out.println("\n---select p.supplier.name from Product p where price > :price and name like :pattern ");
	            String hql = "select p.supplier.name from Product p where price > :price and name like :pattern ";
	            Query query = session.createQuery(hql);
	            query.setParameter("price", 2.0);
	            query.setParameter("pattern", "P%");
	                        
	            List results = query.list();
	            TestUtils.displaySupplierNameList(results);
	        }	        
			
		}
		finally{
			session.close();
		}					

	}

}
