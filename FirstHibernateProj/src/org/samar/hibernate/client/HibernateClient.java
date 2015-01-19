package org.hibernate.training;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.training.model.StudentAddress;
import org.hibernate.training.model.StudentDetails;

public class HibernateClient {

	public static void main(String[] args) {
		StudentDetails student =  new StudentDetails();
		
		student.setId(1);
		student.setStudentName("Student 1");
		
		StudentAddress saddr = new StudentAddress();
		saddr.setCity("Bangalore");
		saddr.setPincode("560048");
		saddr.setState("karnataka");
		saddr.setStreet("WhiteField");

		StudentAddress saddr2 = new StudentAddress();
		saddr2.setCity("Bangalore");
		saddr2.setPincode("560043");
		saddr2.setState("karnataka");
		saddr2.setStreet("Manyata Tech Park");
		
		student.getListOfStudent().add(saddr);
		student.getListOfStudent().add(saddr2);
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
		session.close();
		
		student = null;
		StudentDetails student1 = null;
		session = sf.openSession();
		student = (StudentDetails) session.get(StudentDetails.class, 1);
		student1 = (StudentDetails) session.get(StudentDetails.class, 1);
//		student1 = (StudentDetails) session.load(StudentDetails.class, 1);
		
		session.close();
		System.out.println("student name is "+student.getStudentName());
		
	}

}
