package org.hibernate.tutorial.test;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;
import org.hibernate.tutorial.model.Supplier;

public class Test34 {

	/**
	 * Learning points:
	 * second level cache 
	 * 
	 * @Cache(usage = CacheConcurrencyStrategy.READ_WRIT)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory =  new Configuration().configure(new File("src/hibernateSecondLevelCache.cfg.xml"))
															.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			TestUtils.prepareTestData(session);
			
	        // Enable statistics
	        Statistics stats = sessionFactory.getStatistics();
	        stats.setStatisticsEnabled(true);

	        // Clear first level cache
	        session.clear();
	        
	        // At this point, the second-level cache has the entity

	        // Supplier data will be accessed from cache
	        {
	            System.out.println("\n---Performing load operations");
	            // Because the second-level cache has the entity, it
	            // will be fetched from the second-level cache.
	            // The entity is also cached in the first-level cache as well
	            Supplier supplier = (Supplier) session.load(Supplier.class,new Integer(1));
	            System.out.println(supplier.getName());


	            // fetch the supplier entity again
	            // Because the first-level cache has the entity,
	            // it will be retrieved from the first-cache
	            supplier = (Supplier) session.load(Supplier.class, new Integer(1));
	            System.out.println(supplier.getName());

	            // Evict it from first level cache
	            // Now only Second-level cache has the entity
	            session.evict(supplier);

	            // The entity is fetched from the second-level cache
	            supplier = (Supplier) session.load(Supplier.class, new Integer(1));
	            System.out.println(supplier.getName());

	        }

	        // Display Statistics
	        // The Second-level cache hit is 2 because in the above code,
	        // the entity is fetched from the second-level cache twice
	        // while it is fetched from the first-level cache once.
	        TestUtils.displayStatistics(sessionFactory, stats);

	        // Clear Second level cache
	        TestUtils.evict2ndLevelCache(sessionFactory);

	        // Supplier data will be accessed from cache
	        {
	            System.out.println("\n---Performing load operations");
	            // Entity is fecthed very first time
	            Supplier supplier = (Supplier) session.load(Supplier.class,
	                    new Integer(1));
	            System.out.println(supplier.getName());

	            // fetch the supplier entity again
	            supplier = (Supplier) session.load(Supplier.class, new Integer(1));
	            System.out.println(supplier.getName());

	            // Evict from first level cache
	            session.evict(supplier);

	            supplier = (Supplier) session.load(Supplier.class, new Integer(1));
	            System.out.println(supplier.getName());

	        }

	        // Display Statistics
	        TestUtils.displayStatistics(sessionFactory, stats);			
			
		}
		finally{
			session.close();
		}

	}

}
