package com.learn.Queue;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class UserQueue {
	public static void main(String[] args) throws Exception {
		/*ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String> ();
		q.add("a");
		q.add("b");
		q.add("c");
		q.add("d");
		q.add("e");
		System.out.println(q.poll());
		System.out.println(q.size());
		System.out.println(q.peek());
		System.out.println(q.size());*/
		
		
		//有界阻塞队列
		ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String> (3);
		array.add("a");
		array.put("b");
		array.add("c");
		while(!array.isEmpty()){
			System.out.println(array.take());
		}
		array.offer("d");
		array.offer("e");
		for(Iterator it = array.iterator();it.hasNext();){
			System.out.println(it.next());
		}
		System.out.println(array.offer("a", 2, TimeUnit.SECONDS));
		
		//无界阻塞队列
		/*LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		q.add("f");
		for(Iterator it = q.iterator();it.hasNext();){
			System.out.println(it.next());
		}*/
		
		SynchronousQueue<String> sq = new SynchronousQueue<String>();
		
		
		
		
		
	}
}
