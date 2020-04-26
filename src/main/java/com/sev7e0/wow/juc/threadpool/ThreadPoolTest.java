package com.sev7e0.wow.juc.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Title:  ThreadPoolTest.java
 * description: 线程池主要测试
 *
 * 	线程池主要作用就是控制运行的线程数量，处理过程中将任务放入队列，然后创建线程执行这些任务，如果任务数量超过了线程数量那么线程任务将会
 * 	排队阻塞，直到其他的线程将任务执行完毕，将线程归还给线程池，再将该线程分配给其他任务。
 *
 * 	主要特点：线程复用；控制最大并发数；管理线程
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-07 23:42
 **/

public class ThreadPoolTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolTest.class);


	/**
	 * public ThreadPoolExecutor(int corePoolSize, //核心线程数
	 *                           int maximumPoolSize, //最大线程数
	 *                           long keepAliveTime, // maximumPoolSize - corePoolSize的线程能存活的时间
	 *                           TimeUnit unit, //时间单位
	 *                           BlockingQueue<Runnable> workQueue, //阻塞队列
	 *                           ThreadFactory threadFactory, //生成线程的工厂
	 *                           RejectedExecutionHandler handler) {//拒绝策略：jvm提供四大拒绝策略
	 * }
	 */

	public static void main(String[] args) throws InterruptedException {
//		jvmProvideThreadPool();
		customThreadPool();
	}

	/**
	 * 如何合理设置线程池？
	 * cpu密集型
	 * 		cpu core + 1
	 *
	 * io密集型：
	 * 		1. 由于io密集型任务线程并不是一直在执行，则应该尽可能配置多的线程，如cpu core * 2
	 * 		2. cpu core /1-0.8或者0.9
	 */
	private static void customThreadPool(){

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
			5,
			30L,
			TimeUnit.SECONDS,
			new LinkedBlockingDeque<>(),
			Executors.defaultThreadFactory(),
			new ThreadPoolExecutor.AbortPolicy());
		threadPoolExecutor.execute(()->System.out.println("d"));

		threadPoolExecutor.shutdown();
	}

	private static void jvmProvideThreadPool() throws InterruptedException {
		//固定数线程
		ExecutorService executor = Executors.newFixedThreadPool(5);
		/*模拟十个用户请求*/
		try {
			for (int i = 0; i < 10; i++) {
				/*
				 * Proceed in 3 steps:
				 *
				 * 1. If fewer than corePoolSize threads are running, try to
				 * start a new thread with the given command as its first
				 * task.  The call to addWorker atomically checks runState and
				 * workerCount, and so prevents false alarms that would add
				 * threads when it shouldn't, by returning false.
				 *
				 * 2. If a task can be successfully queued, then we still need
				 * to double-check whether we should have added a thread
				 * (because existing ones died since last checking) or that
				 * the pool shut down since entry into this method. So we
				 * recheck state and if necessary roll back the enqueuing if
				 * stopped, or start a new thread if there are none.
				 *
				 * 3. If we cannot queue task, then we try to add a new
				 * thread.  If it fails, we know we are shut down or saturated
				 * and so reject the task.
				 */
				executor.execute(()-> LOG.info(Thread.currentThread().getName()+"\t newFixedThreadPool"));
			}
		}finally {
			executor.shutdown();
		}

		TimeUnit.SECONDS.sleep(5);
		LOG.warn("=========newFixedThreadPool===========");

		//单一线程
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		/*模拟十个用户请求*/
		try {
			for (int i = 0; i < 10; i++) {
				executorService.execute(()-> LOG.info(Thread.currentThread().getName()+"\t newSingleThreadExecutor"));
			}
		}finally {
			executor.shutdown();
		}

		TimeUnit.SECONDS.sleep(5);
		LOG.warn("=========newSingleThreadExecutor===========");

		//可扩容的线程池
		ExecutorService executorService1 = Executors.newCachedThreadPool();

		//一个接受指定命令的线程池（定时或者延迟）
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
	}
}
