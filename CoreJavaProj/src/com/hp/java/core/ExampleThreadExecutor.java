package com.hp.java.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hp.java.core.threadEg.CallableTaskOne;
import com.hp.java.core.threadEg.CallableTaskTwo;

class ExampleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService pool =  Executors.newCachedThreadPool();
		CallableTaskOne task = new CallableTaskOne();
		CallableTaskTwo task2 = new CallableTaskTwo();
		Future<Integer> result = pool.submit(task);
		Future<Integer> result2 = pool.submit(task2);
		
		try {
			System.out.println("Result of Thread 1 = "+result.get());
			System.out.println("Result of Thread 2 = "+result2.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
