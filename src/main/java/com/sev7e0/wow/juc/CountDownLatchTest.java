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
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		//提供了对线程约束的能力，不可重用，当归零时不会再回到原处。
		CountDownLatch countDownLatch = new CountDownLatch(10);

		ExecutorService executor = Executors.newFixedThreadPool(10);


		for (int i = 0; i < 10; i++) {
			executor.execute(() -> {
				//每一次执行完成后才会计数减一，直到为0
				LOG.info("当前线程：" + Thread.currentThread().getName());
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}

		LOG.info("开始等待多线程任务完成。");
		//countDownLatch 不为0前都会对主线程造成阻塞
		countDownLatch.await();

		LOG.info("任务完成!");

		executor.shutdown();


	}
}
