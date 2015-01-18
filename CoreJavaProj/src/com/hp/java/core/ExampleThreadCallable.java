package com.hp.java.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.hp.java.core.threadEg.CallableTaskOne;
import com.hp.java.core.threadEg.CallableTaskTwo;
import com.hp.java.core.threadEg.RunnableThreadOne;


public class ExampleThreadCallable {

	public static void main(String[] args) {
		CallableTaskOne cTaskOne = new CallableTaskOne();
		CallableTaskTwo cTaskTwo = new CallableTaskTwo();
		
		FutureTask<Integer> taskOne = new FutureTask<Integer>(cTaskOne);		
		Thread t1 = new Thread(taskOne);
		t1.start();
		FutureTask<Integer> taskTwo = new FutureTask<Integer>(cTaskTwo);
		Thread t2 = new Thread(taskTwo);
		t2.start();		
		System.out.println("I am in Main Thread!");
		try {
			Thread.sleep(3000);
			/*System.out.println("Main Thread exit after sleeping..");*/
			System.out.println("Waiting for all threads to exit");
			try {
				System.out.println("Thread 1 result "+taskOne.get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println("Thread 2 result "+taskTwo.get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Main Thread Exit.");
		} catch (InterruptedException e) {
			System.out.println("Main Thread Interrupted!");
			e.printStackTrace();
		}
	}

}
