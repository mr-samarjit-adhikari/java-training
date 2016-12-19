package org.hibernate.tutorial.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="PERSON_DETAILS")
public class PersonDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //using hi-lo algo
	private int id;
	
	@Column(name="SHORT_NAME")
	private String shortName;
	
	//@Transient
	@Column(name="AGE")
	private int age;
	
	//@Temporal(TemporalType.DATE)
	@Column(name="DOB")
	private Date dob;
	
	//@Lob
	@Column(name="DESCRIPTION")
	private String description;
	
	@Embedded
	private Address address;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street",column=@Column(name="OFFICE_STREET")),	
		@AttributeOverride(name="city",column=@Column(name="OFFICE_CITY")),
		@AttributeOverride(name="state",column=@Column(name="OFFICE_STATE")),
		@AttributeOverride(name="pincode",column=@Column(name="OFFICE_PINCODE")),
	})	
	private Address officeAddress;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street",column=@Column(name="HOME_STREET")),	
		@AttributeOverride(name="city",column=@Column(name="HOME_CITY")),
		@AttributeOverride(name="state",column=@Column(name="HOME_STATE")),
		@AttributeOverride(name="pincode",column=@Column(name="HOME_PINCODE")),
	})	
	private Address homeAddress;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
