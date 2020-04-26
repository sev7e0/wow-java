package com.sev7e0.wow.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


/**
 * Title:  OOMUnableToCreateNewNativeThreadTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-16 21:38
 **/

/**
 * 当jvm创建过多线程时，报出的错误，对于本地系统通常对用户的进程数和打开文件数都有限制，
 * 所以当你创建的线程迟迟没有结束释放掉，那么久会发生java.lang.OutOfMemoryError: unable to create new native thread
 *
 * 该问题在kafka 和 hadoop中较为常见。
 *
 */
public class OOMUnableToCreateNewNativeThreadTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(OOMUnableToCreateNewNativeThreadTest.class);

	public static void main(String[] args) {
		int i = 0;
		while (true){
			i++;
			LOG.info(String.valueOf(i));
			new Thread(()->{
				try {
					TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
	}
	/**
	 * 2020-04-20 13:40:15 [main] INFO  OOMUnableToCreateNewNativeThreadTest:29 - 4071
	 * 2020-04-20 13:40:15 [main] INFO  OOMUnableToCreateNewNativeThreadTest:29 - 4072
	 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
	 * 	at java.lang.Thread.start0(Native Method)
	 * 	at java.lang.Thread.start(Thread.java:717)
	 * 	at com.sev7e0.wow.exception.OOMUnableToCreateNewNativeThreadTest.main(OOMUnableToCreateNewNativeThreadTest.java:36)
	 */

}
