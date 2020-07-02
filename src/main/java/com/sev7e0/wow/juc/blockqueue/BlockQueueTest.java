package com.sev7e0.wow.juc.blockqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Title:  BlockQueueTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-04 12:29
 **/

public class BlockQueueTest {

	private static final Logger LOG = LoggerFactory.getLogger(BlockQueueTest.class);


	public static void main(String[] args) throws InterruptedException {


		ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
		LOG.info(String.valueOf(arrayBlockingQueue.add("a")));
		LOG.info(String.valueOf(arrayBlockingQueue.add("b")));
		LOG.info(String.valueOf(arrayBlockingQueue.add("c")));
		LOG.info(arrayBlockingQueue.poll());
		LOG.info(arrayBlockingQueue.take());
		LOG.info(arrayBlockingQueue.peek());

		LOG.info("=========================");

		SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
		ExecutorService executor = Executors.newFixedThreadPool(2);

		executor.execute(() -> {
			try {
				synchronousQueue.put("a");
				LOG.info("produce a");
				synchronousQueue.put("b");
				LOG.info("produce b");
				synchronousQueue.put("c");
				LOG.info("produce c");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		executor.execute(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					String take = synchronousQueue.take();
					LOG.info("consume: {}", take);
					TimeUnit.SECONDS.sleep(5);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		executor.awaitTermination(20, TimeUnit.SECONDS);
		executor.shutdown();

	}
}
