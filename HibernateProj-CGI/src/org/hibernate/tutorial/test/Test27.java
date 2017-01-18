package org.hibernate.tutorial.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.tutorial.model.Product;

public class Test27 {

	/**
	 * CriteriaQuery with Projection
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
			
			{			
				System.out.println("\n---Using critieria with Projection.property() method...");			
				Transaction tx = session.beginTransaction();						
				Criteria criteria = session.createCriteria(Product.class);
				
				ProjectionList pList =  Projections.projectionList();				
				pList.add(Projections.property("name"));
				pList.add(Projections.property("description"));
				pList.add(Projections.property("price"));
				
				criteria.setProjection(pList);
				
				List results =  criteria.list();
				TestUtils.displayObjectsList(results);
				
				tx.commit();
				tx=null;
			}
			
	        // Build a criteria with Projection.max, Projection.min, 
	        // Projection.avg(), Projection.countDistinct() methods
	        {
	        	Transaction tx = session.beginTransaction();
	            System.out.println("\n---Using critieria with Projections.max(), Projections.min(), Projection.avg() method...");
	            Criteria crit = session.createCriteria(Product.class);
	            
	            ProjectionList projList = Projections.projectionList();
	            projList.add(Projections.max("price"));
	            projList.add(Projections.min("price"));
	            projList.add(Projections.avg("price"));
	            projList.add(Projections.countDistinct("description"));
	            
	            crit.setProjection(projList);
	            List results = crit.list();
	            TestUtils.displayObjectsList(results);
	            
				tx.commit();
				tx=null;	            
	        }
	        
	        // Build a criteria with Projections.rowCount() method
	        {
	        	Transaction tx = session.beginTransaction();
	            System.out.println("\n---Using critieria with Projection.rowCount() method...");
	            Criteria crit = session.createCriteria(Product.class);
	            
	            crit.setProjection(Projections.rowCount());
	            
	            List results = crit.list();
	            TestUtils.displayObjectList(results);
	            
				tx.commit();
				tx=null;	            
	        }
			
		}
		finally{
			session.close();
		}

	}

}
