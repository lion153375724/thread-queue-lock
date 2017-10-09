package com.learn.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DaemonThread /*extends Thread*/{
	public static  void init(){
        final Outprint outprint= new Outprint();
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(2000);
                        outprint.out("hadoop");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();


        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(2000);
                        outprint.out("spark");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }

    static  class Outprint{
        Lock lock = new ReentrantLock();
        public   void out(String str) {
            lock.lock();
            try {
                for (int i = 0; i < str.length(); i++) {
                    System.out.print(str.charAt(i));
                }
                System.out.println();
               // lock.unlock();//释放锁（如果上面的代码在unlock之前出错，那么锁将不会被释放，所以最好放到finally中）
            }finally {
                lock.unlock();//释放锁
            }
            }
    }

    public static void main(String[] args) {
    	DaemonThread.init();
    }
	
	/**
	 * 验证sleep/join释放锁的特点
	 * 1、用sleep.方法只在5秒后才运行，说明sleep具有不释放锁的特点
       2、用join.//threadB.bService();方法能被立即调用，说明join方法具有释放锁的特点
	 * @author jason
	 * @createTime 2017年9月30日上午10:01:08
	 */
	/*static class ThreadA extends  Thread{
        private ThreadB b;
        public ThreadA(ThreadB b){
            this.b = b;
        }
        public void run() {
            synchronized (b){
                try {
                    b.start();
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class  ThreadB extends  Thread{
        public void run() {
            try {
                System.out.println("b run begin timer= " + System.currentTimeMillis());
                Thread.sleep(5000);
                //b.join(6000);
                System.out.println("b run end timer=  " + System.currentTimeMillis() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public void bService(){
            System.out.println("打印了bservice timer=  " + System.currentTimeMillis());
        }
    }

    static class ThreadC extends  Thread{
        private ThreadB threadB;
        public ThreadC(ThreadB threadB){
            this.threadB = threadB;
        }

        public void run() {
            threadB.bService();
        }
    }

    public static void main(String[] args) throws InterruptedException {
           ThreadB b = new ThreadB();
           ThreadA a = new ThreadA(b);
           a.start();
           ThreadC c = new ThreadC(b);
           c.start();   //threadB.bService();
           	1、用sleep.方法只在5秒后才运行，说明sleep具有不释放锁的特点
           	2、用join.//threadB.bService();方法能被立即调用，说明join方法具有释放锁的特点
    }*/
    
	
	/**
	 * 一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：

　　1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。

　　2）禁止进行指令重排序。

		volatile不能保证复合操作具有原子性；解决办法就是给操作volatile变量的方法加锁(lock/synchronized)或将变量原子类类型
	 */
	/*private  volatile  boolean isRunning = true;
    public boolean isRunning(){
        return isRunning;
    }

    public void setRunning(boolean isRunning){
        this.isRunning= isRunning;
    }

    public void run(){
        System.out.println("进行了run...............");
        while (isRunning){}
        System.out.println("isUpdated的值被修改为为false,线程将被停止了");
    }

    public static void main(String[] args) throws InterruptedException {
    	DaemonThread volatileThread = new DaemonThread();
        volatileThread.start();
        Thread.sleep(1000);
        volatileThread.setRunning(false);   //通过主线程去修改isRunning值
    }*/
	
	/**
	 * gc后首先调用finalize()方法
	 */
	/*public static void main(String[] args) {
        Object o = new Object(){
            @Override
            protected void finalize() throws Throwable {
               // 一旦垃圾收集器准备好释放对象占用的存储空间，它首先调用finalize()
                System.out.println("running finalize......");
            }
        };
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("running shutdown hook....");
            }
        });

        o = null;
        System.gc();

        System.out.println("Calling system exit");
        System.exit(0);
    }*/
	/**
	 * 多线程访问共享对象和数据的方式(一)
 * 将共享数据封装到另一个对象中，然后这个对象逐一传递给Runable对象，
 * 每个线程对共享数据的操作方式也分配到那个对象的方法上去完成，这样容易实现对该数据进行的各种操作的互斥和通信
	 */
	/*public  static  void main(String[] args){
        final Sharedata sharedata = new Sharedata();
        for(int i = 0; i < 5; i ++) {
            new Thread(new Runnable() {
                public void run() {
                    sharedata.inc();
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    sharedata.dec();
                }
            }).start();
        }
    }

    static  class Sharedata{
        private int count = 10;
        private  boolean flag = true;
        public synchronized void inc(){
            count++;
            System.out.println("线程进行了加操作" + count);
          
        }
        public synchronized  void dec(){
          
            count --;
            System.out.println("线程进行了减操作"+count);
        }
    }*/
    
	/**
	 * timer定时启动任务
	 */
	/*private static Timer timer = new Timer(true); //定时器为守护线程
    static  public class MyTask extends TimerTask{
        public void run() {
            System.out.println("运行时间为："+new Date());
        }
    }

    public static  void main(String[] args){
        MyTask myTask = new MyTask();        //创建定时任务

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2017-09-29 15:33:00";
        try {
            Date date = sdf.parse(dateString);
            System.out.println("任务计划启动时间："+date+"  当前时间："+new Date());
            timer.schedule(myTask,date);
            try {
                Thread.sleep(5000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/
    
	/**
	 * ThreadLocal为解决多线程程序的并发问题提供了一种新的思路。
	 * 使用这个工具类可以很简洁地编写出优美的多线程程序，ThreadLocal并不是一个Thread，
	 * 而是Thread的局部变量。使用ThreadLocal定义的变量在每个线程中是独立的。
	 * 
	 * 一个ThreadLocal代表一个变量，所以其中只能放一个数据，当你有多个变量需要共享时就应该将定义一个对象来包装这些变量再在ThreadLocal对象中存储这个对象
	 */
	    /*public static  void main(String[] args){
	        for (int i = 0 ; i < 2; i ++) {
	            new Thread(new Runnable() {
	                public void run() {
	                    int data = new Random().nextInt();
	                    MyThreadLocal myThreadLocal =  MyThreadLocal.getInstance();
	                    System.out.println("线程:"+Thread.currentThread().getName()+"data  "+data);
	                    myThreadLocal.setName("mobin"+ data);
	                    myThreadLocal.setAge(data);
	                    new A().get();
	                    new B().get();
	                }
	            }).start();
	        }
	    }
	    static class  A{
	        public void get(){
	            MyThreadLocal AThreadLocal = MyThreadLocal.getInstance();
	            System.out.println("A线程"+Thread.currentThread().getName()+"的name"+AThreadLocal.getName()+"    age"+AThreadLocal.getAge());
	        }
	    }

	    static  class B{
	        public void get(){
	            MyThreadLocal BThreadLocal = MyThreadLocal.getInstance();
	            System.out.println("B线程"+Thread.currentThread().getName()+"的name"+BThreadLocal.getName()+"    age"+BThreadLocal.getAge());
	        }
	    }

	    static  class MyThreadLocal{
	        private MyThreadLocal(){}//禁止外部直接创建对象
	        private static  ThreadLocal<MyThreadLocal> mapThreadLocal = new ThreadLocal<MyThreadLocal>();
	        private static  MyThreadLocal getInstance(){
	            MyThreadLocal myThreadLocal = mapThreadLocal.get();
	            if(myThreadLocal == null){
	                myThreadLocal = new MyThreadLocal();
	                mapThreadLocal.set(myThreadLocal);
	            }
	               return myThreadLocal;
	        }

	        private String name;
	        private int age;

	        public String getName() {
	            return name;
	        }

	        public void setName(String name) {
	            this.name = name;
	        }

	        public int getAge() {
	            return age;
	        }

	        public void setAge(int age) {
	            this.age = age;
	        }
	    }*/
}
