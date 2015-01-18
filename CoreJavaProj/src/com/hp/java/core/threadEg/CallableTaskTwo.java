package com.hp.java.core.threadEg;

import java.util.concurrent.Callable;

public class CallableTaskTwo implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		Thread.sleep(3000);
		return 2;
	}

}
