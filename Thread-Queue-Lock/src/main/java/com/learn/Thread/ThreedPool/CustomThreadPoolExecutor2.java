package com.learn.Thread.ThreedPool;

/**
 * 定制属于自己的  阻塞线   程池  
 * 解释：当提交任务被拒绝时，进入拒绝机制，我们实现拒绝方法，把任务重新用阻塞提交方法put提交，实现阻塞提交任务功能，防止队列过大，OOM，提交被拒绝方法在下面 
 * 
 * 总结： 
1、用ThreadPoolExecutor自定义线程池，看线程是的用途，如果任务量不大，可以用无界队列，如果任务量非常大，要用有界队列，防止OOM 
2、如果任务量很大，还要求每个任务都处理成功，要对提交的任务进行阻塞提交，重写拒绝机制，改为阻塞提交。保证不抛弃一个任务 
3、最大线程数一般设为2N+1最好，N是CPU核数 
4、核心线程数，看应用，如果是任务，一天跑一次，设置为0，合适，因为跑完就停掉了，如果是常用线程池，看任务量，是保留一个核心还是几个核心线程数 
5、如果要获取任务执行结果，用CompletionService，但是注意，获取任务的结果的要重新开一个线程获取，如果在主线程获取，就要等任务都提交后才获取，
	就会阻塞大量任务结果，队列过大OOM，所以最好异步开个线程获取结果
 * 
 * @author jason
 * @createTime 2017年9月27日下午3:56:14
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPoolExecutor2 {  
	  
      
    private ThreadPoolExecutor pool = null;  
      
      
    /** 
     * 线程池初始化方法 
     *  
     * corePoolSize 核心线程池大小----1 
     * maximumPoolSize 最大线程池大小----3 
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit 
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES 
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(5)====5容量的阻塞队列 
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂 
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时, 
     *                          即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)), 
     *                                任务会交给RejectedExecutionHandler来处理 
     */  
    public void init() {  
        pool = new ThreadPoolExecutor(  
                1,  
                4,  
                30,  
                TimeUnit.MINUTES,  
                //new ArrayBlockingQueue<Runnable>(5), 
                new LinkedBlockingQueue<Runnable>(),
                new CustomThreadFactory(),  
                new CustomRejectedExecutionHandler());  
    }  
  
      
    public void destory() {  
        if(pool != null) {  
            pool.shutdownNow();  
        }  
    }  
      
      
    public ExecutorService getCustomThreadPoolExecutor() {  
        return this.pool;  
    }  
      
    private class CustomThreadFactory implements ThreadFactory {  
  
        private AtomicInteger count = new AtomicInteger(0);  
          
        @Override  
        public Thread newThread(Runnable r) {  
            Thread t = new Thread(r);  
            String threadName = CustomThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);  
            System.out.println(threadName);  
            t.setName(threadName);  
            return t;  
        }  
    }  
      
      
    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {  
  
        @Override  
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {  
        	try {
                                // 核心改造点，由blockingqueue的offer改成put阻塞方法
				executor.getQueue().put(r);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }  
    }  
      
      
      
    // 测试构造的线程池  
    public static void main(String[] args) {  
    	
        CustomThreadPoolExecutor2 exec = new CustomThreadPoolExecutor2();  
        // 1.初始化  
        exec.init();  
          
        ExecutorService pool = exec.getCustomThreadPoolExecutor();  
        for(int i=1; i<100; i++) {  
            System.out.println("提交第" + i + "个任务!");  
            pool.execute(new Runnable() {  
                @Override  
                public void run() {  
                    try {  
                    	System.out.println(">>>task is running====="); 
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            });  
        }  
          
          
        // 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了  
        // exec.destory();  
          
        try {  
            Thread.sleep(10000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}  
