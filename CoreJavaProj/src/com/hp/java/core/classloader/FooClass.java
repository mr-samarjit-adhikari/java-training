package com.hp.java.core.classloader;

public class FooClass implements FooInterface{

	@Override
	public void foocall() {		
		System.out.println("FooClass: In Foo callback.");
		System.out.println("FooClass Loader is "+FooClass.class.getClassLoader());
	}
	
}
