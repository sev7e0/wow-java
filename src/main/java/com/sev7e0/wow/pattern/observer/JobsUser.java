package com.sev7e0.wow.pattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  JobsUser.java
 * description: 用户实体类
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-23 14:28
 **/

public class JobsUser implements IUser {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JobsUser.class);

	public static void main(String[] args) {
		LOG.info("JobsUser");

	}

	@Override
	public String getUserName() {
		return "Steven Jobs";
	}

	@Override
	public void receiveNews(String msg) {
		LOG.info("{}：receive msg: {}", getUserName(), msg);
	}
}
