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
			prepareTestData(session);
			
			{
				System.out.println("\n---Using criteria with no restriction...");				
				Transaction tx = session.beginTransaction();						
				Criteria criteria = session.createCriteria(Product.class);
				List<Product> products =  criteria.list();
				displayProductsList(products);
				
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
				displayProductsList(products);
				
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
				displayProductsList(products);
				
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
				displayProductsList(products);
				
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
	            displayProductsList(results);
	            
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
	            displaySupplierList(results);
	            
	        }			
			
		}
		finally{
			session.close();
		}

	}
	
	
    public static void displaySupplierList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No suppliers to display.");
            return;
        }
        while (iter.hasNext()) {
            Supplier supplier = (Supplier) iter.next();
            String msg = supplier.getName();
            System.out.println(msg);
        }
    }
    
    public static void displayProductsList(List<Product> list){
        Iterator<Product> iter = list.iterator();
        if (!iter.hasNext()){
            System.out.println("No products to display.");
            return;
        }
        while (iter.hasNext()){
            Product product = (Product) iter.next();
            String msg = product.getSupplier().getName() + "\t";
            msg += product.getName() + "\t";
            msg += product.getPrice() + "\t";
            msg += product.getDescription();
            System.out.println(msg);
        }
    }	

	private static void prepareTestData(Session session) {
		Transaction tx = session.beginTransaction();
		
        Supplier supplier1 = new Supplier();
        supplier1.setName("Supplier Name 1");
        supplier1.setProducts(new ArrayList<Product>());
        session.save(supplier1);
        
        Supplier supplier2 = new Supplier();
        supplier2.setName("Supplier Name 2");
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
		tx = null;
	}

}
