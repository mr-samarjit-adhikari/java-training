package com.hp.java.core.threadEg;

public class ThreadTwo extends Thread{
	public void run(){
		for(int count =0;count<10;count++){
			System.out.println("I Am in Thread Two!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("Thread Two interrupted!");
				e.printStackTrace();
			}
		}
		
		System.out.println("Thread Two Exit!");
	}
}
