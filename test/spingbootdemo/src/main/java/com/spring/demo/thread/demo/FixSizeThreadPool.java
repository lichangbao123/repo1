package com.spring.demo.thread.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import reactor.core.scheduler.Scheduler.Worker;

public class FixSizeThreadPool {
	//创建一个线程池
	//1.创建一个仓库
	private BlockingQueue<Runnable> blockqueue;
	//2.创建一个线程集合
	private List<Thread> workers;
	
	//创建线程
	public static class Worker extends Thread{
		private FixSizeThreadPool pool;

		public Worker(FixSizeThreadPool pool) {
			this.pool = pool;
		}
		
		@Override
		public void run() {
			//开始工作了
			while(this.pool.isWorking||this.pool.blockqueue.size()>0) {
				Runnable task=null;
				//从队列中拿东西时，需要的是阻塞
				try {
					if (this.pool.isWorking) {
						task=this.pool.blockqueue.take();//操作成功之前，阻塞当前线程(null/false)
					} else {
						task=this.pool.blockqueue.poll();
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (task!=null) {
					task.run();
					System.out.println("线程："+Thread.currentThread().getName()+"执行完毕");
				}
			}
		}
	} 
	//线程池的初始化
	public FixSizeThreadPool(int poolsize,int tasksize) {
		if (poolsize<=0||tasksize<=0)
			throw new IllegalArgumentException("非法参数");
		this.blockqueue=new LinkedBlockingQueue<>(tasksize);
		this.workers=Collections.synchronizedList(new ArrayList<>());
		
		for (int i = 0; i < poolsize; i++) {
			Worker worker=new Worker(this);
			worker.start();
			workers.add(worker);
		}
	}
	
	//把任务提交到仓库
	public boolean submit(Runnable runnable) {
		if (isWorking) {
			return this.blockqueue.offer(runnable);
		}else {
			return false;
		}
	}
	/**关闭的方法：
	 * a.仓库停止接受任务
	 * b.若仓库中还有任务，就要继续执行
	 * c.单任务时，就不该阻塞了
	 * d.若是已经阻塞，就去中断它
	 */
	private volatile boolean isWorking=true;
	public void shutDown() {
		//执行关闭即可
		this.isWorking=false;
		
		for (Thread thread:workers) {
			if (thread.getState().equals(Thread.State.BLOCKED)) {
				thread.interrupt();//中断线程
			}
		}
	}
	
	public static void main(String[] args) {
		FixSizeThreadPool pool=new FixSizeThreadPool(3, 6);
		for (int i = 0; i < 6; i++) {
			pool.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println("放入一个线程");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		pool.shutDown();
	}
}
