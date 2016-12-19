package org.hibernate.tutorial.test;

import java.util.Date;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Event;
import org.hibernate.tutorial.model.Speaker;

public class Test14 {

	/**
	 * Learning Point;
	 * @OneToMany relationship 
	 * cascade
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Event event = new Event();
			event.setName("Hibernate OpenHouse Event");
			event.setDuration(5);
			event.setStartDate(new Date());
			event.setSpeakers(new HashSet<Speaker>());
			
			event.getSpeakers().add(new Speaker("SpeakerFirstname1","LastName1"));
			event.getSpeakers().add(new Speaker("SpeakerFirstname2","LastName2"));
			event.getSpeakers().add(new Speaker("SpeakerFirstname3","LastName3"));
					
			session.persist(event);//Need to use persist() and not save() due to cascade
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
