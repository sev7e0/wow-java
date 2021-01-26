package com.sev7e0.wow.innerclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  StaticInnerClassTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-07-13 15:16
 **/

public class StaticInnerClassTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StaticInnerClassTest.class);

	public static void main(String[] args) {
		LOG.info("StaticInnerClassTest");

		InnerClass innerClass = new InnerClass();
		innerClass.setB("cc");
		System.out.println(innerClass.getB());
		System.out.println(InnerClass.a);
		System.out.println(InnerClass.get());

	}

	private static class InnerClass{

		//可以定义静态变量
		private static String a = "a";
		private String b = "b";

		public InnerClass() {
			System.out.println("可以自定义构造方法");
		}

		public static String get(){
			return a;
		}

		/**
		 * 可以定义方法
		 * @return b
		 */
		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}
	}

}
