package com.sev7e0.wow.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  ReferenceConst.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-03 08:38
 **/

public class ReferenceConst {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ReferenceConst.class);

	public static void main(String[] args) {
		LOG.info("ReferenceConst");
		LOG.info("这里引用了Const类种的常量：{}",Const.value);
		/**
		 * 输出：
		 * 2020-06-03 08:40:30 [main] INFO  ReferenceConst:23 - ReferenceConst
		 * 2020-06-03 08:40:30 [main] INFO  ReferenceConst:24 - finalString
		 *
		 * 可以看到Const类并没有被初始化，实际上是因为常量在编译阶段就已经加入到常量池中了，
		 * 本质上并没有引用Const类，所以其没有被初始化。
		 */
	}

}

class Const{
	static {
		System.out.println("init const class!");
	}
	//编译期间就已经载入到常量池
	public static final String value = "finalString";
}