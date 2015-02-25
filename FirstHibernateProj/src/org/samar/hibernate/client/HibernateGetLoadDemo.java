package org.samar.hibernate.client;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.samar.hibernate.model.Student;

public class HibernateGetLoadDemo {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();		
		Session session = sessionFactory.openSession();
		session.beginTransaction(); // Transaction has been started.
		
		/* Use case: Run HibernateClient.java to create a student relation in DB.
		 * Then set hbm2ddl.auto = create and run this client. It will delete all
		 * rows in student relation. Now if we use get(Student.class,1) , it will
		 * return null and load(Student.class,1) will return ObjectNotFoundException error */
		
		//Student student1 = (Student) session.get(Student.class, 1);
		Student student1 = (Student) session.load(Student.class, 1);
		student1.setStudentName("Samarjit1");
		session.persist(student1);
	
		session.getTransaction().commit(); // Transaction is ended here.
		session.close();
	}

}
