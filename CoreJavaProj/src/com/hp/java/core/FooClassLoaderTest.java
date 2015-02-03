package com.hp.java.core;

import com.hp.java.core.classloader.FooClassLoader;
import com.hp.java.core.classloader.FooInterface;

public class FooClassLoaderTest {

	public static void main(String[] args) {
		ClassLoader cl =  new FooClassLoader();	
		Class<?> fooClass = null;
		try {
			ClassLoader currLoader = FooClassLoaderTest.class.getClassLoader(); 
			System.out.println("MainClass Loader :"+currLoader);
			
			fooClass = cl.loadClass("com.hp.java.core.classloader.FooClass");
			System.out.println("Loaded "+fooClass.getName());		
			
			Object fooObj = fooClass.newInstance();
			((FooInterface) fooObj).foocall();

		}
		catch(NullPointerException e){
			System.out.println("loadClass return NULL");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
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
