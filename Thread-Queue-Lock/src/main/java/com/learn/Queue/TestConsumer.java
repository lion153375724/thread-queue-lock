package com.learn.Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestConsumer {
	public static void main(String[] args) throws InterruptedException {
		//LinkedBlockingQueue<String>  queue = new LinkedBlockingQueue<String>();
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(4);
		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i=0;i<20;i++){
			executor.submit(new Producer(queue));
			
		}
		Thread.sleep(2000);
		executor.submit(new Consumer(queue));
		executor.shutdown();
	}
}
