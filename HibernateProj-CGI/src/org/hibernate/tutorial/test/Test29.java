package org.hibernate.tutorial.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.tutorial.model.Product;
import org.hibernate.tutorial.model.Supplier;

public class Test29 {

	/**
	 * Learning points:
	 * Query by example 
	 * @param args
	 */
	public static void main(String[] args) {

		SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			prepareTestData(session);
			
			{
				System.out.println("\n---Using criteria with Query By Example: Get all suppliers whose name is SamSung");
				Supplier supplierSS = new Supplier();
				supplierSS.setName("SamSung");
				
				Example exampleRestriction = Example.create(supplierSS);
				Criteria criteria = session.createCriteria(Supplier.class);
				criteria.add(exampleRestriction);
				
				List supplierList = criteria.list();
				TestUtils.displaySupplierList(supplierList);				
			}

			{
				System.out.println("\n---Using criteria with Query By Example 2: Get all products whose supplier is SamSung");
				Supplier supplier = new Supplier();
				supplier.setName("SamSung");
				
				Criteria supplierCriteria = session.createCriteria(Product.class)
										           .createCriteria("supplier");
				
				supplierCriteria.add(Example.create(supplier));
				List productList = supplierCriteria.list();
				TestUtils.displayProductsList(productList);
				
			}
		}
		finally{
			session.close();
		}			
		
	}
	
    private static void prepareTestData(Session session){
        Transaction tx = session.beginTransaction();
        
        Supplier supplier1 = new Supplier();
        supplier1.setName("SamSung");
        supplier1.setProducts(new ArrayList<Product>());
        session.save(supplier1);
        
        Supplier supplier2 = new Supplier();
        supplier2.setName("Sony");
        supplier2.setProducts(new ArrayList<Product>());
        session.save(supplier2);
        
        Product product1 = new Product("Product 1","Name for Product 1", 2.0);
        product1.setSupplier(supplier1);
        supplier1.getProducts().add(product1);
        session.save(product1);
        
        Product product12 = new Product("Product 2","Name for Product 2", 22.0);
        product12.setSupplier(supplier1);
        supplier1.getProducts().add(product12);
        session.save(product12);
        
        Product product2 = new Product("Product 3", "Name for Product 3", 30.0);
        product2.setSupplier(supplier2);
        supplier2.getProducts().add(product2);
        session.save(product2);
        
        tx.commit();
        tx=null;
    }	

}
