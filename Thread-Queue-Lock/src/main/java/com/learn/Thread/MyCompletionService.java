package com.learn.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCompletionService implements Callable<String>{
	private int id;
	
	public MyCompletionService(){
	}
	
	public MyCompletionService(int id){
		this.id = id;
	}
	
	public void process() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletionService<String> CompletionService = new ExecutorCompletionService<String>(executor);
		
		for(int i=0;i<10;i++){
			//运行时调用call()方法:方式一
			//CompletionService.submit(new MyCompletionService(i));
			String result = String.valueOf(i);
			//方式2
			CompletionService.submit(new Callable(){
				@Override
				public Object call() throws Exception {
					return result;
				}
				
				
			});
		}
		for(int i=0;i<10;i++){
			//获取call()返回值
			System.out.println(CompletionService.take().get());
			System.out.println(CompletionService.poll().get());
		}
		executor.shutdown();
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyCompletionService cs =  new MyCompletionService();
		cs.process();
	}

	@Override
	public String call() throws Exception {
		System.out.println(this.id + "Start!");
		Thread.sleep((long) (Math.random()*10000));
		System.out.println(this.id + "End!");
		return this.id + "";
	}
}
