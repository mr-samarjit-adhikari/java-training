package org.hibernate.tutorial.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tutorial.model.Student;
import org.hibernate.tutorial.model.Vehicle;


public class Test13 {

	/**
	 * Learning point:
	 * Mapping; - one to one
	 * a student has single vehicle.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Vehicle vehicle = new Vehicle();
			vehicle.setName("Bike");
			session.save(vehicle);
			
			Student student = new Student();
			student.setName("Student 1");
			student.setVehicle(vehicle);
			
			session.save(student);
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
