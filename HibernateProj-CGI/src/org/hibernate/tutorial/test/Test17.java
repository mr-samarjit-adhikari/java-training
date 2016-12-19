package org.hibernate.tutorial.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Book;
import org.hibernate.tutorial.model.InternationalBook;
import org.hibernate.tutorial.model.SpecialEditionBook;

public class Test17 {

	/**
	 * Learning Points:
	 * Inheritance:- single table strategy 
	 *  
	 * @param args
	 */
	public static void main(String[] args) {
				
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
			Book book = new Book("Hibernate Book","Base Author",new Date(),55.00);
			Book sbook = new SpecialEditionBook("Special Hibernate Book", "SAuthor", new Date(), 99.00, "With Demo");
			Book ibook = new InternationalBook("International Hibernate Book", "IAuthor", new Date(), 155.00, "English", "ABC");
			
			session.save(book);
			session.save(sbook);
			session.save(ibook);
			
			tx.commit();
		}
		catch(Exception e){
			if(tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
	}		
}
