package com.learn.Queue;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread{
	private BlockingQueue<String> queue;
	
	public Consumer(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	public void run(){
		while(true){
			try {
				System.out.println("消费:" + queue.take() + "---------------");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
