package com.sev7e0.wow.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Title:  OOMGCOverheadLimitExceededTest.java
 * description: GCOverheadLimitExceeded 测试用例
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-16 08:18
 **/

public class OOMGCOverheadLimitExceededTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(OOMGCOverheadLimitExceededTest.class);

	public static void main(String[] args) {
		LOG.info("OOMGCOverheadLimitExceededTest");
		Integer count = 0;
		Set<String> strings = new HashSet<>();
		String str = "OOMGCOverheadLimitExceededTest";
		try {
			while (true) {
				// 每次都创建一个新的字符串对象，并将其使用强引用进行保留，以保证gc不会回收
				// 很块，将会发生full GC，gc时间过长（百分之98的时间）并且回收了不到百分之
				// 二堆内存，那么将会发生该异常。
				strings.add((str + count).intern());
				count++;
			}
		} catch (Exception e) {
			System.out.println("i=" + count);
			e.printStackTrace();
			throw e;
		}
		/*
		  //异常输出
		  Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
		  	at com.sev7e0.wow.exception.OOMGCOverheadLimitExceededTest.main(OOMGCOverheadLimitExceededTest.java:33)
		 */
	}

}
