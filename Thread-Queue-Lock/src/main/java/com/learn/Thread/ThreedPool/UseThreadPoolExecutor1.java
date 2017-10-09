package com.learn.Thread.ThreedPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;

public class UseThreadPoolExecutor1 {
	public static void main(String[] args) throws Exception {
		/**
		 * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize,则优先创建线程
			若大于 corePoolSize，则会将任务加入队列
			若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
			若线程数大于maximumPoolSize,则执行拒绝策略，或其它自定义方式。
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				3,		//coreSize
				3,			//maxSize
				20,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2)   //指定一种队列 (有界队列)
				//new LinkedBlockingQueue<Runnable>()
				,new DiscardOldestPolicy()
				);
		
		MyTask mt1 = new MyTask(1,"任务1");
		MyTask mt2 = new MyTask(2,"任务2");
		MyTask mt3 = new MyTask(3,"任务3");
		MyTask mt4 = new MyTask(4,"任务4");
		MyTask mt5 = new MyTask(5,"任务5");
		MyTask mt6 = new MyTask(6,"任务6");
		MyTask mt7 = new MyTask(7,"任务7");
		MyTask mt8 = new MyTask(8,"任务8");
		MyTask mt9 = new MyTask(9,"任务9");
		MyTask mt10 = new MyTask(10,"任务10");
		
		pool.execute(mt1);
		pool.execute(mt2);
		pool.execute(mt3);
		pool.execute(mt4);
		pool.execute(mt5);
		pool.execute(mt6);
		pool.execute(mt7);
		pool.execute(mt8);
		pool.execute(mt9);
		pool.execute(mt10);
		
		pool.shutdown();
				
				
				
				
				
				
				
				
				
				
	}
}
