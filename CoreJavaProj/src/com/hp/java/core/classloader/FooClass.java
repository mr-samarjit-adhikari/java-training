package com.hp.java.core.classloader;

public class FooClass implements FooInterface{

	public FooClass() {
//		System.out.println("In Constructor");
//		System.out.println("FooClass was loaded by "+FooClass.class.getClassLoader());		
	}
	
	@Override
	public void foocall() {		
		//System.out.println("FooClass: In Foo callback.");
	}
	
}
