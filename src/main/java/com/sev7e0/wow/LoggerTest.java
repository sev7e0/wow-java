package com.sev7e0.wow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  LoggerTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-16 21:12
 **/

public class LoggerTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LoggerTest.class);

	public static void main(String[] args) {
		LOG.info("LoggerTest");

		try {
			throwException();
		} catch (Exception e) {
			//正确的打印日志
			LOG.error("Exception encountered！:{}", e.getMessage(), e);
		}


	}

	private static void throwException() {
		throw new RuntimeException("wow throw exception!");
	}

}
