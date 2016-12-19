package org.hibernate.tutorial.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("SpecialEditionBook")
public class SpecialEditionBook extends Book {
	
	public SpecialEditionBook(String title, String author, Date purchasedDate, 
								double cost,String features) {
		
		super(title, author, purchasedDate, cost);
		this.newFeatures = features;
	}

	private String newFeatures;

	public String getNewFeatures() {
		return newFeatures;
	}

	public void setNewFeatures(String newFeatures) {
		this.newFeatures = newFeatures;
	}
		
}
