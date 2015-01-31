package com.hp.java.core;

import java.lang.reflect.Method;

public class ReflectionTest {

	public static void main(String[] args) {
		// Access RunnableThreadOne Object and invoke the run method
		// No need to import com.hp.java.core.threadEg
//		final String runClassStr = "com.hp.java.core.threadEg.RunnableThreadOne";
		final String runClassStr = "com.hp.java.core.threadEg.ThreadTwo";
		Class<?>	 runClass		= null;
		
		try {
			runClass = Class.forName(runClassStr);
			Method[] allDeclMethods = runClass.getDeclaredMethods();
			Method[] allMethods     = runClass.getMethods();
			boolean  foundRunMeth 	= false;

			/* Validate if run method is available */
			for(Method m :allDeclMethods){
				String mName = m.getName();
				if(mName.equals("run")){
					foundRunMeth = true;
					break;
				}		
			}

			if(false == foundRunMeth){
				for(Method m: allMethods){
					if(m.equals("run")){
						foundRunMeth = true;
						break;
					}		
				}
				
			}
			
			if(false == foundRunMeth){
				throw new ClassNotFoundException("Did not find 'run' method in class "+runClassStr);
			}

			/*Runnable interface could not be used here as newInstance do not take
			 * any argument */
			Thread 	 threadObj   = (Thread)runClass.newInstance();
			threadObj.start();
			
			System.out.println("I am in Reflection thread!");
			threadObj.join();
			
		} 
		catch(InterruptedException e){
			System.out.println("Thread Interrupted !");
			e.printStackTrace();
		}
		catch (InstantiationException e){
			e.printStackTrace();
		}
		catch(IllegalAccessException e){
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Class Not found!");
			e.printStackTrace();
		}
		
	}

}
