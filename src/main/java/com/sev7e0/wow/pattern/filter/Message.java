package com.sev7e0.wow.pattern.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  Message.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-01 15:09
 **/

public class Message {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(Message.class);

	public static void main(String[] args) {
		LOG.info("Message");

	}

	private String content;

	private Integer id;


}
