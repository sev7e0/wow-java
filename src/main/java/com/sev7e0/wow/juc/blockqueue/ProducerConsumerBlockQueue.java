package com.sev7e0.wow.juc.blockqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Title:  ProducerConsumerBlockQueue.java
 * description: 基于block queue实现的消息队列
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-06 23:40
 **/

public class ProducerConsumerBlockQueue {


	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		ProdCons<String> stringProdCons = new ProdCons<>(new ArrayBlockingQueue<>(4));

		//启动生产者线程
		executor.execute(() -> {
			try {
				stringProdCons.produce("data");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		TimeUnit.SECONDS.sleep(10);
		//启动消费者线程
		executor.execute(() -> {
			try {
				stringProdCons.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		//主线程暂停20s
		TimeUnit.SECONDS.sleep(20);
		//调用stop方法
		stringProdCons.stop();
		executor.shutdown();
	}
}

class ProdCons<T> {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ProducerConsumerBlockQueue.class);

	private volatile boolean FLAG = true;

	AtomicInteger atomicInteger = new AtomicInteger(0);

	private final BlockingQueue<T> blockingQueue;

	public ProdCons(BlockingQueue<T> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void produce(T t) throws InterruptedException {
		T nt = null;
		while (FLAG) {
			int i = atomicInteger.incrementAndGet();
			if (t instanceof String) {
				nt = (T) (t + String.valueOf(i));
			}
			boolean result = blockingQueue.offer(nt, 2, TimeUnit.SECONDS);
			if (result) {
				LOG.info("produce data :{}", nt.toString());
			} else {
				LOG.warn("produce data fail :{}", nt.toString());
				FLAG = false;
			}
			TimeUnit.SECONDS.sleep(1);
		}
		LOG.info("stop produce data~");
	}

	public void consume() throws InterruptedException {
		while (FLAG) {
			T result = blockingQueue.poll(5, TimeUnit.SECONDS);
			if (Objects.isNull(result)) {
				FLAG = false;
				LOG.warn("stop consume data");
				return;
			}
			LOG.info("consume data success: {}", result);
		}
	}


	public void stop() {
		this.FLAG = false;
		blockingQueue.clear();
		LOG.info("shut down");
	}


}