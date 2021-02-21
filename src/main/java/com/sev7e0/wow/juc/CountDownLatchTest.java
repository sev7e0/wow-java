package com.sev7e0.wow.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Title:  CountdownLatchTest.java
 * description: CountDownLatch test，内部也是使用AQS实现的
 * countDown()使用了sync.releaseShared(1);
 * await()使用了sync.acquireSharedInterruptibly(1);可中断，
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-01 16:00
 **/

public class CountDownLatchTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CountDownLatchTest.class);


	/**
	 * 当前线程：pool-1-thread-1--倒数：8
	 * 当前线程：pool-1-thread-2--倒数：8
	 * 当前线程：pool-1-thread-2--倒数：7
	 * 当前线程：pool-1-thread-1--倒数：6
	 * 当前线程：pool-1-thread-2--倒数：5
	 * 当前线程：pool-1-thread-1--倒数：4
	 * 当前线程：pool-1-thread-2--倒数：3
	 * 当前线程：pool-1-thread-1--倒数：2
	 * 当前线程：pool-1-thread-2--倒数：1
	 * 当前线程：pool-1-thread-1--倒数：0
	 * end!
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startLatch = new CountDownLatch(1);
		CountDownLatch countDownLatch = new CountDownLatch(10);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.execute(() -> {
				try {
//					LOG.info("线程：{} 准备就绪", Thread.currentThread().getName());
					startLatch.await();
					Thread.sleep(5_000);
					LOG.info("线程：{} 执行完成", Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}

			});
		}
		startLatch.countDown();
		countDownLatch.await();
		LOG.info("所有线程都执行完成");
		executor.shutdown();
	}
}
