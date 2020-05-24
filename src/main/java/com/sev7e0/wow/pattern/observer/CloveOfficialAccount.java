package com.sev7e0.wow.pattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  CloveOffcialAccount.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-23 14:24
 **/

public class CloveOfficialAccount  extends AbsOfficialAccount{

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CloveOfficialAccount.class);

	public static void main(String[] args) {
		LOG.info("CloveOffcialAccount");

	}

	@Override
	protected void follow(IUser user) {
		userList.add(user);
		LOG.info("用户：{}，订阅了丁香园公众号", user.getUserName());
	}

	@Override
	protected void unFollow(IUser user) {
		userList.remove(user);
		LOG.info("用户：{}，取关了丁香园公众号", user.getUserName());
	}

}
