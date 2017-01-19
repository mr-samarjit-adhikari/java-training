package org.hibernate.tutorial.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.tutorial.model.Product;
import org.hibernate.tutorial.model.Supplier;

public class TestUtils {
	
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

	public static void prepareTestData(Session session) {
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
	
    public static void displayObjectsList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No objects to display.");
            return;
        }
        while (iter.hasNext()) {
            System.out.println("New object");
            Object[] obj = (Object[]) iter.next();
            for (int i=0;i<obj.length;i++) {
                System.out.println(obj[i]);
            }
        }
    }
	

    public static void displayObjectList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No objects to display.");
            return;
        }
        while (iter.hasNext()) {
            Object obj = iter.next();
            
            System.out.println(obj);
        }
    }    
    
    public static void displaySupplierNameList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No suppliers to display.");
            return;
        }
        while (iter.hasNext()) {
            String supplier = (String) iter.next();
            System.out.println(supplier);
        }
    }  
    
    public static void displayPriceList(List list) {
        Iterator iter = list.iterator();
        if (!iter.hasNext()) {
            System.out.println("No price to display.");
            return;
        }
        while (iter.hasNext()) {
            Double price = (Double) iter.next();
            System.out.println(price);
        }
    }        
}
