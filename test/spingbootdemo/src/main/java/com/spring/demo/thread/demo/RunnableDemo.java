package com.spring.demo.thread.demo;

public class RunnableDemo {
	
	public static class TestRunnable implements Runnable{

		@Override
		public void run() {
			System.out.println("当前线程:"+Thread.currentThread().getName());
		}

	}
	
	public static void main(String[] args) {
		TestRunnable test=new TestRunnable();
		new Thread(test).start();
	}
}
