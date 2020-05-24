package com.sev7e0.wow.pattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  JordanUser.java
 * description: 用户实体类
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-23 14:29
 **/

public class JordanUser implements IUser{

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JordanUser.class);

	public static void main(String[] args) {
		LOG.info("JordanUser");

	}

	@Override
	public String getUserName() {
		return "Michael Jordan";
	}

	@Override
	public void receiveNews(String msg) {
		LOG.info("{}：receive msg: {}",getUserName(), msg);
	}
}
