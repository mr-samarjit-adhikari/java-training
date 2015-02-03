package com.hp.java.core;

import com.hp.java.core.classloader.FooClass;
import com.hp.java.core.classloader.FooClassLoader;

public class FooClassLoaderTest {

	public static void main(String[] args) {
		ClassLoader cl =  new FooClassLoader();	
		Class<?> fooClass = null;
		try {
			fooClass = cl.loadClass("com.hp.java.core.classloader.FooClass");
			System.out.println("Loaded "+fooClass.getName());			
			Object fooObj = fooClass.newInstance();
			((FooClass) fooObj).foocall();

		} catch (ClassNotFoundException e) {
			System.out.println("Could not find Class");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("Not able to create new instance of Class "+ fooClass.getName());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccess for class "+ fooClass.getName());
			e.printStackTrace();
		}
	}

}
