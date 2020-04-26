package com.sev7e0.wow.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * Title:  OOMDirectBufferMemoryTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-16 08:47
 **/

public class OOMDirectBufferMemoryTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(OOMDirectBufferMemoryTest.class);

	public static void main(String[] args) {
		LOG.info("OOMDirectBufferMemoryTest");
		LOG.info("当前最大堆外内存：" + sun.misc.VM.maxDirectMemory() / 1024 / 1024 + "m");
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
		ByteBuffer byteBuffer1 = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
		ByteBuffer byteBuffer3 = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
		ByteBuffer byteBuffer4 = ByteBuffer.allocateDirect(1024 * 1024 * 1024);
	}


}
