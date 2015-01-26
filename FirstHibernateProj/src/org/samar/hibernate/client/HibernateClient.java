package org.samar.hibernate.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.samar.hibernate.model.StudentAddress;
import org.samar.hibernate.model.Student;

public class HibernateClient {

	public static void main(String[] args) {
		Student student =  new Student();
		
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
		Student student1 = null;
		session = sf.openSession();
		student = (Student) session.get(Student.class, 1);
		student1 = (Student) session.get(Student.class, 1);
//		student1 = (StudentDetails) session.load(StudentDetails.class, 1);
		
		session.close();
		System.out.println("student name is "+student.getStudentName());
		
	}

}
