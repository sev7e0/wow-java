package com.sev7e0.wow.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Title:  OOMJavaHeapSpaceTest.java
 * description: JAVA OOM之JavaHeapSpace
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-16 08:10
 **/

public class OOMJavaHeapSpaceTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(OOMJavaHeapSpaceTest.class);

	public static void main(String[] args) {
		LOG.info("OOMJavaHeapSpaceTest");
		OOMJavaHeapSpaceTest oomJavaHeapSpaceTest = new OOMJavaHeapSpaceTest();
		oomJavaHeapSpaceTest.applyNewHeapSpace();
		/*
		  异常输出：
		  Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
		  	at com.sev7e0.wow.exception.OOMJavaHeapSpaceTest.applyNewHeapSpace(OOMJavaHeapSpaceTest.java:29)
		  	at com.sev7e0.wow.exception.OOMJavaHeapSpaceTest.applyNewHeapSpace(OOMJavaHeapSpaceTest.java:30)
		  	at com.sev7e0.wow.exception.OOMJavaHeapSpaceTest.applyNewHeapSpace(OOMJavaHeapSpaceTest.java:30)
		  	at com.sev7e0.wow.exception.OOMJavaHeapSpaceTest.main(OOMJavaHeapSpaceTest.java:25)
		 */
	}

	private void applyNewHeapSpace() {
		//循环在堆上创建大对象，并将其引用起来起，使其无法被回收
		ArrayList<byte[]> bytes1 = new ArrayList<>();
		while (true){
			byte[] bytes = new byte[1024 * 1024 * 1024];
			bytes1.add(bytes);
		}
	}

}
