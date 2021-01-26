package com.sev7e0.wow.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Title:  ThreadLocalTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-27 21:37
 **/

public class ThreadLocalTest {

	private static final ThreadLocal<Long> localTime = new ThreadLocal<>();

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ThreadLocalTest.class);

	public static void main(String[] args) {
		LOG.info("ThreadLocalTest");

		Thread thread1 = new Thread(() -> {
			localTime.set(System.currentTimeMillis());
			test();
		});
		thread1.start();

		Thread thread2 = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			localTime.set(System.currentTimeMillis());
			test();
		});
		thread2.start();

		Thread thread3 = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			localTime.set(System.currentTimeMillis());
			test();
		});
		thread3.start();

		/**
		 * 输出：
		 * 2020-06-27 21:43:04 [main] INFO  ThreadLocalTest:27 - ThreadLocalTest
		 * 2020-06-27 21:43:04 [Thread-1] INFO  ThreadLocalTest:61 - 当前线程获取到的时间为：1593265384486
		 * 2020-06-27 21:43:06 [Thread-2] INFO  ThreadLocalTest:61 - 当前线程获取到的时间为：1593265386488
		 * 2020-06-27 21:43:07 [Thread-3] INFO  ThreadLocalTest:61 - 当前线程获取到的时间为：1593265387489
		 */
		localTime.remove();
	}


	private static void test() {
		LOG.info("当前线程获取到的时间为：{}", localTime.get());
	}

}
