package com.spring.demo.thread.demo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable<String> callable=new Callable<String>() {
			@Override
			public String call() throws Exception {
				Random random=new Random();
				return "返回值："+random.nextInt(200);
			}
		};
		
		FutureTask<String> futureTask=new FutureTask<>(callable);
		new Thread(futureTask).start();
		System.out.println(futureTask.get());
	}
	
}
