package org.hibernate.tutorial.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tutorial.model.Product;

public class Test26 {
	
	/**
	 * CriteriaQuery with SQL restriction example 
	 * @param args
	 */

	public static void main(String[] args) {
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
			
			{
				/**
				 * Apply a constraint expressed in SQL. 
				 * Any occurrences of {alias} will be replaced by the table name alias.
				 */
				
				System.out.println("\n---Using criteria with Restrictions.sqlRestriction...");			
				Transaction tx = session.beginTransaction();						
				Criteria criteria = session.createCriteria(Product.class);
				criteria.add(Restrictions.sqlRestriction("{alias}.name like 'P%' "));
				
				List<Product> products =  criteria.list();
				TestUtils.displayProductsList(products);
				
				tx.commit();
				tx=null;
			}
		}
		finally{
			session.close();
		}

	}

}
