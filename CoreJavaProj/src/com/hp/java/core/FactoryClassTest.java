package com.hp.java.core;

public class FactoryClassTest {

	public static void main(String[] args) {
		//Meal m = Meal.VegMeal;
		Meal m = Meal.NonVegMeal;
		System.out.println("Retured from Factory as "+FactoryDemo.getMeal(m));

	}

}
