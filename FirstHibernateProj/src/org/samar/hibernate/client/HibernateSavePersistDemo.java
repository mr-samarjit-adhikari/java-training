package org.samar.hibernate.client;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.samar.hibernate.model.Student;

public class HibernateSavePersistDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();		
		Session session = sessionFactory.openSession();
		session.beginTransaction(); // Transaction has been started.
		
		Student newStudent = new Student();
		newStudent.setStudentDescription("This is new Student Description.");
		
		session.save(newStudent);
	
		session.getTransaction().commit(); // Transaction is ended here.
		session.close();
		
		newStudent.setStudentName("Name is Samarjit");

		Session session2 = sessionFactory.openSession();
		session2.beginTransaction(); // Transaction has been started.		
		
		session2.persist(newStudent);
		
		session2.getTransaction().commit(); // Transaction is ended here.
		session2.close();
		
	}
}