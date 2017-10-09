package com.learn.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class MyFuture {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0; i<10; i++){
			String result = "test" + i;
			FutureTask<String>  list = new FutureTask<String>(new Callable(){
				@Override
				public Object call() throws Exception {
					return result;
				}
			});
			exec.submit(list);
			Thread.sleep(1000);
			System.out.println("result:"+list.get());
		}
		exec.shutdown();
	}
}
