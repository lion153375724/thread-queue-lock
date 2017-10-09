package com.learn.Thread.ThreedPool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UserExecutors {
	public static void main(String[] args) {
		Executor executor = Executors.newFixedThreadPool(10);  
		Runnable task = new Runnable() {  
		    public void run() {  
		        System.out.println("task over");  
		    }  
		};  
		executor.execute(task);  
		  
		executor = Executors.newScheduledThreadPool(10);  
		ScheduledExecutorService scheduler = (ScheduledExecutorService) executor;  
		scheduler.scheduleAtFixedRate(task, 10, 10, TimeUnit.SECONDS);  
	}
}
