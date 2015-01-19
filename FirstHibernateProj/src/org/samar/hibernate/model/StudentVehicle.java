package org.samar.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="VEHICLE_TABLE")
public class StudentVehicle {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name="vehicle_id")
	private int		id;
	private String	type;
	private String  brand;
	@ManyToOne
	private Student	mappedstudent;
	
	public Student getStudent() {
		return mappedstudent;
	}
	public void setStudent(Student student) {
		this.mappedstudent = student;
	}
	public int getVehicle_id() {
		return id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.id = vehicle_id;
	}

	public String getVehicle_type() {
		return type;
	}
	public void setVehicle_type(String vehicle_type) {
		this.type = vehicle_type;
	}
	public String getVehicle_brand() {
		return brand;
	}
	public void setVehicle_brand(String vehicle_brand) {
		this.brand = vehicle_brand;
	}
	
}
