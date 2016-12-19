package org.hibernate.tutorial.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("InternationalBook")
public class InternationalBook extends Book {

	public InternationalBook(String title, String author, Date purchasedDate, 
								double cost,String language, String religion) 
	{
		super(title, author, purchasedDate, cost);
		this.language = language;
		this.religion = religion;
	}
	private String language;
	private String religion;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	
	
}
