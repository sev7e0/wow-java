package com.sev7e0.wow.ref;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  StrongReferenceTest.java
 * description: java 强引用记录
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-13 08:31
 **/
/**
 * 在java中强引用也就是正常创建的一个对象，如new出来的
 * 强引用对象的特点：
 * 		- 对象在引用过程中发生gc不会进行回收
 * 		- 对象大小超过内存限制时会发生oom
 **/
public class StrongReferenceTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StrongReferenceTest.class);

	public static void main(String[] args) {
		LOG.info("StrongReferenceTest");
		/*
		 * 强引用指在程序中只要有一个强引用指向对象（也就是GCRoots可达状态），那么这个对象就表示还活着，
		 * 发生gc时也不会进行回收，即使该对象永远也不会被用到也不会进行回收。这也就是java中内存泄露的主
		 * 要原因之一。
		 */
		Object o = new Object();
		Object c = o;
		System.out.println(c);
		o = null;
		System.gc();
		System.out.println(c);
		System.out.println(o);
	}

}
