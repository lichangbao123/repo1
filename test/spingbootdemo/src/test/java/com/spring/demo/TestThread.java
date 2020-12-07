package com.spring.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread implements Runnable{
	public static int i=0;
	
	@Override
	public void run() {
		System.out.println("start thread……"+i);
		i++;
		//Thread.yield();//建议线程机制进行切换
		System.out.println("end thread……"+i);
	}
	
	public static void main(String[] args) {
		/**
		 * 	任务 run 方法会有某种形式的循环，使得任务一直运行下去直到不再需要，所以要设定 run 方法的跳出条件
		 * 	Thread.yield() 可以使用线程调度，它的意思是建议线程机制进行切换：你已经执行完重要的部分了，剩下的交给其他线程跑一跑吧。
		 * 	注意是建议执行，而不是强制执行。
		 */
		/*for (int i = 0; i < 5; i++) {
			TestThread tt=new TestThread();
			tt.run();
		}*/
		
		/**
		 * Thread 构造器只需要一个 Runnable 对象，调用 Thread 对象的 start() 方法为该线程执行必须的初始化操作，然后调用 Runnable 
		 * 	的 run 方法，以便在这个线程中启动任务。
		 * 	可以看到，在 run  方法还没有结束前，run 就被返回了。也就是说，程序不会等到 run 方法执行完毕就会执行下面的指令。
		 */
		/*for (int i = 0; i < 5; i++) {
			Thread th=new Thread(new TestThread());
			th.start();
		}
		System.out.println("Waiting thread ...");*/
		
		/**
		 * JDK1.5 的java.util.concurrent 包中的执行器 Executor 将为你管理 Thread 对象，从而简化了并发编程。
		 * Executor 在客户端和任务之间提供了一个间接层；与客户端直接执行任务不同，这个中介对象将执行任务。 
		 * Executor 允许你管理异步任务的执行，而无须显示地管理线程的生命周期。
		 * 	我们使用 Executor 来替代上述显示创建 Thread 对象。CachedThreadPool 为每个任务都创建一个线程。
		 * 	注意：ExecutorService 对象是使用静态的 Executors 创建的，这个方法可以确定 Executor 类型。
		 * 		对 shutDown 的调用可以防止新任务提交给 ExecutorService ，这个线程在 Executor 中所有任务完成后退出。
		 */
		/*ExecutorService service = Executors.newCachedThreadPool();
		  for(int i = 0;i < 5;i++){
		    service.execute(new TestThread());
		  }
		  service.shutdown();*/
		
		/**
		 * FixedThreadPool 使你可以使用有限的线程集来启动多线程
		 * 	有了 FixedThreadPool 使你可以一次性的预先执行高昂的线程分配，因此也就可以限制线程的数量。
		 * 	这可以节省时间，因为你不必为每个任务都固定的付出创建线程的开销。
		 */
		 /*ExecutorService service = Executors.newFixedThreadPool(5);
		  for(int i = 0;i < 5;i++){
		    service.execute(new TestThread());
		  }
		  service.shutdown();*/
		  
		/**
		 * SingleThreadExecutor 就是线程数量为 1 的 FixedThreadPool，如果向 SingleThreadPool 一次性提交了多个任务，
		 * 	那么这些任务将会排队，每个任务都会在下一个任务开始前结束，所有的任务都将使用相同的线程。
		 * SingleThreadPool 会序列化所有提交给他的任务，并会维护它自己(隐藏)的悬挂队列。
		 * 
		 * 	从输出的结果就可以看到，任务都是挨着执行的。我为任务分配了五个线程，但是这五个线程不像是我们之前看到的有换进换出的效果，
		 * 	它每次都会先执行完自己的那个线程，然后余下的线程继续“走完”这条线程的执行路径。
		 * 	你可以用 SingleThreadExecutor 来确保任意时刻都只有唯一一个任务在运行。
		 */
		  ExecutorService service = Executors.newSingleThreadExecutor();
		  for(int i = 0;i < 5;i++){
		    service.execute(new TestThread());
		  }
		  service.shutdown();
	}
}
