package com.sev7e0.wow.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  StringEquals.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-04 23:36
 **/

public class StringEquals {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StringEquals.class);

	public static void main(String[] args) {
		LOG.info("StringEquals");
		String a = new String("1");
		String b = new String("1");

		LOG.info("{}",a == b);
		LOG.info("{}",a.equals(b));

	}

}
