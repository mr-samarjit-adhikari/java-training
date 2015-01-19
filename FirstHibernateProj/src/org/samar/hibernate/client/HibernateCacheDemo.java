package org.samar.hibernate.client;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.samar.hibernate.model.Student;

public class HibernateCacheDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();		
		Session session = sessionFactory.openSession();
		session.beginTransaction(); // Transaction has been started.
		
		Student student1 = (Student) session.get(Student.class, 12);
		student1.setStudentName("Samarjit1");
		session.persist(student1);
	
		session.getTransaction().commit(); // Transaction is ended here.
		session.close();
		
//		Session session2 = sessionFactory.openSession();
//		session2.beginTransaction(); // Transaction has been started.
//		
//		Student student2 = (Student) session2.get(Student.class, 12);
//
//		session2.getTransaction().commit(); // Transaction is ended here.
//		session2.close();
		
	}
}
