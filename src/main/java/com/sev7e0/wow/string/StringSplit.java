package com.sev7e0.wow.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  StringSplit.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-10 13:48
 **/

public class StringSplit {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StringSplit.class);

	public static void main(String[] args) {
		LOG.info("StringSplit");

		String str = "this is a test";
		String nullString = "";
		String methodStr = "method:com";

		LOG.info("{}", str.split(" ")[0]);
		LOG.info("{}", nullString.split(" ").length);
		LOG.info("{}", methodStr.split(":").length < 2);

	}

}
