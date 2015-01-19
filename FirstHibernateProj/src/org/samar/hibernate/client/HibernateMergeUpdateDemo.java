package org.samar.hibernate.client;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.samar.hibernate.model.Student;

public class HibernateMergeUpdateDemo {
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();		
		Session session = sessionFactory.openSession();
		session.beginTransaction(); // Transaction has been started.
	
		Student student1 = (Student) session.get(Student.class, 2);
	
		session.getTransaction().commit(); // Transaction is ended here.
		session.close();
		
		student1.setStudentName("New Name Samarjit Adhikari");

		Session session2 = sessionFactory.openSession();
		session2.beginTransaction(); // Transaction has been started.		
		
		Student student2 = (Student) session2.get(Student.class, 2);
		session2.merge(student1);
		//session2.update(student1);
		
		session2.getTransaction().commit(); // Transaction is ended here.
		session2.close();
		
	}
}
