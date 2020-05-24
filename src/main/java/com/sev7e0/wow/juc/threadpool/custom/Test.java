package com.sev7e0.wow.juc.threadpool.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.Soundbank;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Title:  Test.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-11 15:13
 **/

public class Test {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) throws InterruptedException {

		ExecutorService pool = new ExecutorServicePool(5, new ArrayBlockingQueue<>(20));

		for (int i = 0; i < 25; i++) {
			pool.execute(()-> {
				try {
					TimeUnit.SECONDS.sleep(1);
					LOG.info(Thread.currentThread().getName());
				}catch (InterruptedException e){
					LOG.info("");
				}
			});
		}

		TimeUnit.SECONDS.sleep(10);
		pool.destroy();
	}

}
