package org.samar.hibernate.client;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.samar.hibernate.model.Student;
import org.samar.hibernate.model.StudentAddress;
import org.samar.hibernate.model.StudentVehicle;

public class HibernateClient2 {

	public static void main(String[] args) {

		Student st1 = new Student();
		st1.setStudentName("First Student");
		st1.setStudentDescription("First Student description.");
		
		StudentVehicle veh1 = new StudentVehicle();
		veh1.setVehicle_type("Two Wheeler");
		veh1.setVehicle_brand("Pulser");
		veh1.setStudent(st1);;
		
		StudentVehicle veh2 = new StudentVehicle();
		veh2.setVehicle_type("Four Wheeler");
		veh2.setVehicle_brand("Honda");		
		veh2.setStudent(st1);
		
		st1.getVehicleList().add(veh1);
		st1.getVehicleList().add(veh2);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();		
		Session session = sessionFactory.openSession();
		session.beginTransaction(); // Transaction has been started.

		session.save(veh1);
		session.save(veh2);	
		session.save(st1); // save the object
	
		session.getTransaction().commit(); // Transaction is ended here.
		session.close();
		
	}

}
