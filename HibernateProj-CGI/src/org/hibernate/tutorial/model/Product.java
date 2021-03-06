package org.hibernate.tutorial.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name="HQLpricing", query="select product.price from Product product where product.price > :price")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@ManyToOne
    private Supplier supplier;
    
    private String name;
    private String description;
    private double price;
    
    public Product(){
    	super();
    }
    
    public Product(String name, String description, double price) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
    }    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
