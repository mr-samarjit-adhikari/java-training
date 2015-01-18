package com.hp.java.core.threadEg;

public class RunnableThreadOne implements Runnable{

	@Override
	public void run() {
		for (int count=0;count<10;count++){
			System.out.println("In AM in Thread One");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread One Interrupted!!");
				e.printStackTrace();
			}
		}
		
		System.out.println("Thread One Exit!");
	}

}
