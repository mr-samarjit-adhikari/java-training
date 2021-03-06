package org.hibernate.tutorial.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Attendee;
import org.hibernate.tutorial.model.Event;
import org.hibernate.tutorial.model.Location;
import org.hibernate.tutorial.model.Speaker;

public class Test16 {

	/**
	 * Learning Points: 
	 * @ManyToMany and MappedBy
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			
			Location loc1 = new Location();
			loc1.setAddress("Bangalore");
			loc1.setEventList(new ArrayList<>());
			
			Location loc2 = new Location();
			loc2.setAddress("Calcutta");	
			loc2.setEventList(new ArrayList<>());
			
			Event event = new Event();
			event.setName("Hibernate OpenHouse Event");
			event.setDuration(5);
			event.setStartDate(new Date());
			event.setSpeakers(new HashSet<Speaker>());
			event.setAttendees(new HashSet<Attendee>());
			event.setLocaltionList(new ArrayList<Location>());
			
			event.getSpeakers().add(new Speaker("SpeakerFirstname1","LastName1"));
			
			loc1.getEventList().add(event);
			loc2.getEventList().add(event);
			
			event.getLocaltionList().add(loc1);
			event.getLocaltionList().add(loc2);
			session.persist(event);//Need to use persist() and not save() due to cascade
							
			Attendee attendee = new Attendee();
			attendee.setFirstName("AttendeeFirstName1");
			attendee.setLastName("AttendeeLastName1");
			attendee.setEvent(event);
			
			Attendee attendee1 = new Attendee();
			attendee1.setFirstName("AttendeeFirstName2");
			attendee1.setLastName("AttendeeLastName2");
			attendee1.setEvent(event);			
			
			session.save(attendee);
			session.save(attendee1);
			event.getAttendees().add(attendee);
			event.getAttendees().add(attendee1);
			
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
