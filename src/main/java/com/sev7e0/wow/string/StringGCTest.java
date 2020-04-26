package com.sev7e0.wow.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Title:  StringGCTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-14 13:21
 **/

public class StringGCTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StringGCTest.class);

	public static void main(String[] args) throws InterruptedException {
		LOG.info("StringGCTest");
		String originStr = "jdk"+args.length;
		String newStr = new String("Use new keyword defined String literal.");
		String oriStr = "Use new keyword defined String literal.";
		String intern = newStr.intern();

		LOG.info(originStr);
		LOG.info(newStr);
		originStr = null;
		System.gc();
		TimeUnit.SECONDS.sleep(2);

		LOG.info(String.valueOf(intern == oriStr));
	}

}
