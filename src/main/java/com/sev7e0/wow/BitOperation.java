package com.sev7e0.wow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  BitOperation.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-09-12 21:05
 **/

public class BitOperation {

	private static final int MAXIMUM_CAPACITY = 16;

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BitOperation.class);

	public static void main(String[] args) {
		LOG.info("BitOperation");
		int a = 100;
		int b = a >>>1;
		ConcurrentHashMap(0x7fffffff);
		System.out.println(0x7fffffff);

	}
	public static void ConcurrentHashMap(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException();
		int cap = ((initialCapacity >= (MAXIMUM_CAPACITY >>> 1)) ? MAXIMUM_CAPACITY : tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
		LOG.info(String.valueOf(cap));
	}
	/**
	 * 返回大于等于count的最小的2的幂次方
	 */
	private static int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

}
