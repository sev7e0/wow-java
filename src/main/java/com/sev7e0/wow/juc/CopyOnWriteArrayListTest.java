package com.sev7e0.wow.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Title:  CopyOnWriteArrayListTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-28 08:26
 **/

public class CopyOnWriteArrayListTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CopyOnWriteArrayListTest.class);

	public static void main(String[] args) {
		LOG.info("CopyOnWriteArrayListTest");
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

		ArrayList<Integer> integers = new ArrayList<>(); // 抛出异常：java.util.ConcurrentModificationException

		for (int i = 0; i < 10; i++) {

			int finalI = i;
			executor.execute(()-> copyOnWriteArrayList.add(finalI));

			executor.execute(()->{
				for (Integer integer : copyOnWriteArrayList){
					LOG.info("test：{}", integer);

				}
				LOG.info("此次遍历结束！");
			});

		}


	}

}
