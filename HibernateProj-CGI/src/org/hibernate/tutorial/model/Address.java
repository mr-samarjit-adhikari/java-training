package org.hibernate.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * Address is valueType object as it does not have any existence by own.
 * Address always has to be associated to other entity. 
 */

@Embeddable
public class Address {
	@Column(name="STREET_NAME")
	private String street;
	@Column(name="CITY_NAME")
	private String city;
	@Column(name="STATE_NAME")
	private String state;
	private int pincode;
	
	public String getStreets() {
		return street;
	}
	public void setStreets(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
}
