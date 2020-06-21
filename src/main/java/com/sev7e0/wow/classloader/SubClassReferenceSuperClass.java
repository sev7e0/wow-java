package com.sev7e0.wow.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  SubClassReferenceSuperClass.java
 * description: 子类引用父类字段，只会初始化父类
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-03 08:28
 **/

public class SubClassReferenceSuperClass {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SuperClass.class);

	public static void main(String[] args) {
		LOG.info("SuperClass");
		LOG.info("这里引用了父类字段，子类中并没有定义：{}", SubClass.value);
		/*
		 * 输出：
		 * 2020-06-03 08:32:30 [main] INFO  SuperClass:23 - SuperClass
		 * parent class init
		 * 2020-06-03 08:32:30 [main] INFO  SuperClass:24 - 这里引用了父类字段，子类中并没有定义：10
		 */
	}

}

class SuperClass{
	static {
		System.out.println("Super class init");
	}
	public static int value = 10;
}

class SubClass extends SuperClass{

	static {
		System.out.println("Sub class init");
	}

}
