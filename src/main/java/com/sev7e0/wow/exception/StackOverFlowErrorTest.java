package com.sev7e0.wow.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  StackOverFlowErrorTest.java
 * description: StackOverFlowError 测试用例
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-16 08:00
 **/

public class StackOverFlowErrorTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StackOverFlowErrorTest.class);

	public static void main(String[] args) {
		LOG.info("StackOverFlowErrorTest");

		StackOverFlowErrorTest stackOverFlowErrorTest = new StackOverFlowErrorTest();
		byte[] bytes = new byte[1000 * 1024 * 1024];
		stackOverFlowErrorTest.recursionMethod(bytes);

		/*
		  异常输出：
		  Exception in thread "main" java.lang.StackOverflowError
		  	at com.sev7e0.wow.exception.StackOverFlowErrorTest.recursionMethod(StackOverFlowErrorTest.java:32)
		  	at com.sev7e0.wow.exception.StackOverFlowErrorTest.recursionMethod(StackOverFlowErrorTest.java:32)
		  	at com.sev7e0.wow.exception.StackOverFlowErrorTest.recursionMethod(StackOverFlowErrorTest.java:32)
		 */
	}

	public void recursionMethod(byte[] bytes) {
		//递归调用，将方法栈打满
		recursionMethod(bytes);
	}
}
