package com.sev7e0.wow.queue;

import com.sev7e0.wow.myfunction.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Title:  DelayTask.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2021-01-30 18:29
 **/

public class DelayTask implements Delayed {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DelayTask.class);

	private long time;
	private String name;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DelayTask(String name, long time, TimeUnit unit) {
		this.name = name;
		this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
	}

	/**
	 *
	 * @param unit the time unit
	 * @return the remaining delay; zero or negative values indicate
	 * that the delay has already elapsed
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return time - System.currentTimeMillis();
	}

	/**
	 *
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it
	 *                              from being compared to this object.
	 */
	@Override
	public int compareTo(Delayed o) {
		DelayTask task = (DelayTask) o;
		long diff = this.time - task.time;
		if (diff <= 0) {
			return -1;
		} else {
			return 1;
		}
	}
}
