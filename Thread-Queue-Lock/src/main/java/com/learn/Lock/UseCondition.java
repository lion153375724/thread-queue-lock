package com.learn.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ondition，Condition 将 Object 监视器方法（wait、notify 和 notifyAll）分解成截然不同的对象，
 * 以便通过将这些对象与任意 Lock 实现组合使用，为每个对象提供多个等待 set （wait-set）。
 * 其中，Lock 替代了 synchronized 方法和语句的使用，Condition 替代了 Object 监视器方法的使用
 * @author jason
 * @createTime 2017年9月28日上午10:08:59
 */
public class UseCondition {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void method1(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待状态..");
			Thread.sleep(2000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁..");
			condition.await();	// Object wait
			System.out.println("当前线程：" + Thread.currentThread().getName() +"继续执行...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入..");
			Thread.sleep(2000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒..");
			condition.signal();		//Object notify
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		final UseCondition uc = new UseCondition();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.method1();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.method2();
			}
		}, "t2");
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.method2();
			}
		}, "t3");
		t1.start();
		
		t2.start();
		t3.start();
	}
	
	
	
}
