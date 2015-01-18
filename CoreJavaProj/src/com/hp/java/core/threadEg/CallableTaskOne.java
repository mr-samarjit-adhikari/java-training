package com.hp.java.core.threadEg;

import java.util.concurrent.Callable;

public class CallableTaskOne  implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		Thread.sleep(2000);
		return 1;		
	}

}
