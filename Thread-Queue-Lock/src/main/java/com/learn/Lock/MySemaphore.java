package com.learn.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore
一个计数信号量。从概念上讲，信号量维护了一个许可集合。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release() 添加一个许可，
从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
Semaphore 通常用于限制可以访问某些资源（物理或逻辑的）的线程数目。例如，下面的类使用信号量控制对内容池的访问：
这里是一个实际的情况，大家排队上厕所，厕所只有两个位置，来了10个人需要排队。
 * @author jason
 * @createTime 2017年9月28日上午9:36:01
 */
public class MySemaphore extends Thread{

	private int id;
	Semaphore position;
	
	public MySemaphore(int id,Semaphore position){
		this.id = id;
		this.position = position;
	}
	
	public void run(){
		try {
			/*if(position.availablePermits() > 0){//获取当前信号量可用的许可
				System.out.println("顾客【 " + this.id + "】进入厕所，有空位");
			}else{
				System.out.println("顾客【 " + this.id + "】排队等队，无空位");
			}*/
			position.acquire();
			System.out.println("顾客【 " + this.id + "】获得空位");
			Thread.sleep((int)(2000));
			System.out.println("顾客【 " + this.id + "】使用完毕");
			System.out.println("---------------------------------------------");
			position.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(2);
		for(int i=0; i<10; i++){
			executorService.submit(new MySemaphore(i+1,semaphore));
		}
		executorService.shutdown();
		//方法acquireUninterruptibly()的作用是使等待进入acquire()方法的线程，不允许被中断
		/*semaphore.acquireUninterruptibly(2);
		System.out.println("全部使用完毕");
		semaphore.release(2);*/
		
	}
}
