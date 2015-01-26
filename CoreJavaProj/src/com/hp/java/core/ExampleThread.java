package com.hp.java.core;

import com.hp.java.core.threadEg.RunnableThreadOne;
import com.hp.java.core.threadEg.ThreadTwo;

public class ExampleThread {
	
	public static void  main(String[] args) {
		RunnableThreadOne runnableThreadOneObj = new RunnableThreadOne();
		Thread threadOne = new Thread(runnableThreadOneObj);
		threadOne.start();
		
		ThreadTwo threadTwo = new ThreadTwo();
		threadTwo.start();
		System.out.println("I am in Main Thread!");
		try {
			Thread.sleep(3000);
			/*System.out.println("Main Thread exit after sleeping..");*/
			System.out.println("Waiting for all threads to exit");
			threadOne.join();
			threadTwo.join();
			System.out.println("Main Thread Exit.");
		} catch (InterruptedException e) {
			System.out.println("Main Thread Interrupted!");
			e.printStackTrace();
		}
	}

}
