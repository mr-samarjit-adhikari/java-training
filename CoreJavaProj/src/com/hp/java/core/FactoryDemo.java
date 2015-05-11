package com.hp.java.core;

public class FactoryDemo {
	public static String getMeal(Meal m){
		if(m.name() == Meal.NonVegMeal.toString()){
			return "nonVeg";
		}
		else{
			return "Veg";
		}
	}
}
