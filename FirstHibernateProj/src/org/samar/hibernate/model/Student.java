package org.samar.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


@Entity(name="STUDENT_ENTITY")
@Table(name="STUDENT_DETAILS")
public class Student {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int Id;
	private String studentName;	
	@Column(name="student_description")		
	private String studentDescription;
	
	@OneToMany(mappedBy = "mappedstudent")
	List<StudentVehicle> vehicleList = new ArrayList<StudentVehicle>();
	
	
	public List<StudentVehicle> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(List<StudentVehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
	public String getStudentDescription() {
		return studentDescription;
	}
	public void setStudentDescription(String studentDescription) {
		this.studentDescription = studentDescription;
	}
	
	//@ElementCollection(fetch=FetchType.EAGER)
	@ElementCollection //By default it is FetchType.LAZY
	@JoinTable(name = "STUDENT_ADDRESS",
				joinColumns = @JoinColumn(name="STUDENT_ID")
	)
	@GenericGenerator(name="hilogen",strategy="hilo")
	@CollectionId(columns = { @Column (name="STUDENT_ADDRID") }, generator = "hilogen", 
				  type = @Type(type="long"))	
	Collection<StudentAddress> listOfStudentAddrs = new ArrayList<StudentAddress>();

	public Collection<StudentAddress> getListOfStudent() {
		return listOfStudentAddrs;
	}
	public void setListOfStudent(Collection<StudentAddress> listOfStudent) {
		this.listOfStudentAddrs = listOfStudent;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
