package com.learn.Queue;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread{
	
	private BlockingQueue<String> queue;
	
	public Producer(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	public void run(){
		String temp = "生产进程:" + Thread.currentThread().getName();
		System.out.println("生产进程:" + Thread.currentThread().getName());
		try {
			queue.put(temp);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
