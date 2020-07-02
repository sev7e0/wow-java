package com.sev7e0.wow.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Title: FutureTaskTest.java
 * description:
 *
 * @author Lijiaqi
 * @version 1.0
 * @since 2018-12-05 11:13
 **/

public class FutureTaskTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FutureTaskTest.class);

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {
			int result = 0;
			for (int i = 0; i < 30; i++) {
				LOG.info("当前任务正在运行：{}", result);
				TimeUnit.SECONDS.sleep(1);
				result += i;
			}
			return result;
		});

		ExecutorService executor = Executors.newFixedThreadPool(1);


		executor.execute(integerFutureTask);


		//会将当前线程阻塞,直到get()方法有返回
		System.out.println("i will be block");
		System.out.println(integerFutureTask.get());
		unSafe();
	}

	public static class ThreadUnsafeExample {

		private int cnt = 0;

		public void add() {
			cnt++;
		}

		public int get() {
			return cnt;
		}
	}

	public static void unSafe() {
		final int threadSize = 1000;
		ThreadUnsafeExample example = new ThreadUnsafeExample();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < threadSize; i++) {
			executorService.execute(example::add);
		}
		executorService.shutdown();
		System.out.println(example.get());
	}
}
