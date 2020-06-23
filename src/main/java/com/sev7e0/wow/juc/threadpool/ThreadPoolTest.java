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

	public static void main(String[] args) throws InterruptedException {
		jvmProvideThreadPool();
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
	/**
	 * ExecutorService 中方法execute的执行步骤，主要分为三步:
	 *
	 * 1. 如果运行的线程少于corePoolSize，请尝试
	 * 以给定的命令作为第一个任务来启动新线程(调用addWorker)。对addWorker的调用以原子方式检查runState和workerCount，
	 * 从而通过返回false来防止在不应该添加threads的错误警报。
	 *
	 * 2. 如果线程大于等于corePoolSize，则将任务放置到队列中，若任务成功排队，那么我们仍然需要
	 * 重新检查是否应该添加一个线程（因为自上次检查以来已有的线程已死亡）
	 * 或者池在进入此方法后已关闭。因此，我们重新检查状态，如果停止，则回滚排队，如果没有，则启动新线程。
	 *
	 * 3. 如果无法将任务排队，则尝试添加新的线程。如果失败了，
	 * 我们知道我们已经被关闭或者饱和了，所以拒绝这个任务（使用指定的拒绝策略）。
	 */
	private static void customThreadPool(){

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			2, //核心线程数：也就是线程池中存在的线程数，及时没有工作需要线程去做也是存在的
			5, //最大线程数，当线程不够时将会创建新的线程数，但不会创建超过该值的数量
			30L, //当 核心线程数 < 当前线程 <= 最大线程数 时，多于核心线程数的线程如果在这个时间内没有接收到新的任务那么他就会被关闭
			TimeUnit.SECONDS, //等待时间单位
			new LinkedBlockingDeque<>(), //当达到最大线程数时，再进来的任务将会被放到这个队列中
			Executors.defaultThreadFactory(), //当线程不够时产生线程的工厂
			new ThreadPoolExecutor.AbortPolicy());// 当队列也满时，将会使用该拒绝策略进行拒绝
		threadPoolExecutor.execute(()->LOG.info(Thread.currentThread().getName()+"\t customThreadPool"));

		threadPoolExecutor.shutdown();
	}

	private static void jvmProvideThreadPool() throws InterruptedException {
		// 定长线程池，该线程池重用固定数量的线程在共享的无边界队列上操作。
		//可控制最大线程数，使用时需要合理估算出线程数量
		ExecutorService executor = Executors.newFixedThreadPool(5);
		/*模拟十个用户请求*/
		try {
			for (int i = 0; i < 10; i++) {
				executor.execute(()-> LOG.info(Thread.currentThread().getName()+"\t newFixedThreadPool"));
			}
		}finally {
			executor.shutdown();
		}

		TimeUnit.SECONDS.sleep(2);
		LOG.warn("=========newFixedThreadPool===========");

		//单一线程，用于执行任务，当这个线程挂掉时会被其他的线程代替
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		/*模拟十个用户请求*/
		try {
			for (int i = 0; i < 10; i++) {
				executorService.execute(()-> LOG.info(Thread.currentThread().getName()+"\t newSingleThreadExecutor"));
			}
		}finally {
			executor.shutdown();
		}

		TimeUnit.SECONDS.sleep(2);
		LOG.warn("=========newSingleThreadExecutor===========");

		//可扩容的线程池，线程池大小受操作系统或者jvm限制，一般情况下需要手动控制并发任务数
		//以此来控制线程数。
		ExecutorService executorService1 = Executors.newCachedThreadPool();

		//一个接受指定命令的线程池（定时或者延迟），可以周期性的执行任务。
		//线程池大小固定，底层使用ScheduledThreadPoolExecutor
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		scheduledExecutorService.scheduleAtFixedRate(
			()->{LOG.info(Thread.currentThread().getName()+"\t newScheduledThreadPool");},
			0,
			3000,
			TimeUnit.MILLISECONDS);

	}
}
