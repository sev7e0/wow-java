package com.sev7e0.wow.innerclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  InnerClassTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-07-13 15:22
 **/

public class InnerClassTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(InnerClassTest.class);

	public static void main(String[] args) {
		LOG.info("InnerClassTest");

		final InnerClassTest classTest = new InnerClassTest();
		classTest.test();
	}
	private void test(){
		InnerClass innerClass = new InnerClass("a");
		System.out.println(innerClass.getA());
	}

	private class InnerClass{
		//内部类不能有静态声明
		//private static String a = "a";

		private final String a;

		public InnerClass(String a) {
			System.out.println("可以有构造方法");
			this.a = a;
		}

		//public static String get(){}

		//可以自定义方法
		public String getA(){
			return a;
		}
	}

}
