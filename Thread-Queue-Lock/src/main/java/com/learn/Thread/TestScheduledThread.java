package com.learn.Thread;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestScheduledThread {
	public static void main(String[] args) throws InterruptedException {
		final ScheduledExecutorService exec = new ScheduledThreadPoolExecutor(2);
		final Runnable runnable = new Runnable() {
			int count = 0;

			@Override
			public void run() {
				System.out.println(new Date() + "run:" + count++);
			}
		};

		// 1秒钟后运行，并每隔2秒运行一次
		final ScheduledFuture beeperHandle = exec.scheduleAtFixedRate(runnable,
				1, 2, TimeUnit.SECONDS);
		// 2秒钟后运行，并每次在上次任务运行完后等待5秒后重新运行
		final ScheduledFuture beeperHandle2 = exec.scheduleWithFixedDelay(
				runnable, 2, 5, TimeUnit.SECONDS);
		// 30秒后结束关闭任务，并且关闭Scheduler
		exec.schedule(new Runnable() {
			@Override
			public void run() {
				beeperHandle.cancel(true);
				beeperHandle2.cancel(true);
				exec.shutdown();
			}

		}, 20, TimeUnit.SECONDS);
	}
}
