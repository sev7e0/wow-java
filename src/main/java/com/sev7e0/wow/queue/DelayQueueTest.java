package com.sev7e0.wow.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Title:  DelayQueueTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2021-01-30 18:32
 **/

public class DelayQueueTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DelayQueueTest.class);

	public static void main(String[] args) {
		LOG.info("DelayQueueTest");
		DelayQueue<DelayTask> delayTasks = new DelayQueue<>();
		DelayTask delayTask01 = new DelayTask("延迟消息---01", 20, TimeUnit.SECONDS);
		DelayTask delayTask02 = new DelayTask("延迟消息---02", 10, TimeUnit.SECONDS);

		delayTasks.offer(delayTask01);
		delayTasks.offer(delayTask02);

		while (true){
			DelayTask poll = delayTasks.poll();
			if (Objects.isNull(poll)){
				continue;
			}
			LOG.info(poll.getName()+"========="+poll.getTime());
		}
	}

}
