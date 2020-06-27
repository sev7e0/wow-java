package com.sev7e0.wow.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Title:  CyclicBarrierTest.java
 * description: CyclicBarrier test
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-01 17:04
 **/

public class CyclicBarrierTest {

	/**
	 * 线程：pool-1-thread-1到达barrier，开始等待其他的线程到达
	 * 线程：pool-1-thread-2到达barrier，开始等待其他的线程到达
	 * 所有线程都已到达， pool-1-thread-2继续执行
	 * 所有线程都已到达， pool-1-thread-1继续执行
	 * 线程：pool-1-thread-2到达barrier，开始等待其他的线程到达
	 * 线程：pool-1-thread-1到达barrier，开始等待其他的线程到达
	 * 所有线程都已到达， pool-1-thread-1继续执行
	 * 所有线程都已到达， pool-1-thread-2继续执行
	 * 线程：pool-1-thread-1到达barrier，开始等待其他的线程到达
	 * 线程：pool-1-thread-2到达barrier，开始等待其他的线程到达
	 * 所有线程都已到达， pool-1-thread-2继续执行
	 * 所有线程都已到达， pool-1-thread-1继续执行
	 * 线程：pool-1-thread-2到达barrier，开始等待其他的线程到达
	 * 线程：pool-1-thread-1到达barrier，开始等待其他的线程到达
	 * 所有线程都已到达， pool-1-thread-1继续执行
	 * 所有线程都已到达， pool-1-thread-2继续执行
	 * 线程：pool-1-thread-1到达barrier，开始等待其他的线程到达
	 * 线程：pool-1-thread-2到达barrier，开始等待其他的线程到达
	 * 所有线程都已到达， pool-1-thread-2继续执行
	 * 所有线程都已到达， pool-1-thread-1继续执行
	 * @param args
	 */
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

		//注意线程池数量不能小于parties数量，因为需要进行线程阻塞。
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			executorService.execute(()->{
				try {
					System.out.println("线程："+Thread.currentThread().getName()+"到达barrier，开始等待其他的线程到达");
					//CyclicBarrier会等待，直到满足了定义了parties到达
					cyclicBarrier.await();
					//满足条件后所有线程继续执行
					System.out.println("所有线程都已到达， " +Thread.currentThread().getName()+"继续执行");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}


	/**
	 *
	 * 可以使用超时回调的cyclicBarrier
	 */
	public void cyclicBarrierWithTimeout(){

	}

}


