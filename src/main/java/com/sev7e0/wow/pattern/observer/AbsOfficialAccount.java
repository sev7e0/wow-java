package com.sev7e0.wow.pattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:  AbsOfficialAccount.java
 * description: 公众号抽象类
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-23 14:17
 **/

public abstract class AbsOfficialAccount {

	protected List<IUser> userList = new ArrayList<>();

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AbsOfficialAccount.class);

	public static void main(String[] args) {
		LOG.info("AbsOfficialAccount");

	}

	protected abstract void follow(IUser user);
	protected abstract void unFollow(IUser user);

	protected void pushNews(String msg){
		userList.forEach(user-> user.receiveNews(msg));
	}


}
