package com.sev7e0.wow.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;

/**
 * Title:  ZeroCopyTest.java
 * description: 主要用来验证零拷贝技术的性能
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-04 12:29
 **/

public class ZeroCopyTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ZeroCopyTest.class);


	/**
	 * 2020-04-05 16:34:04 [main] INFO  ZeroCopyTest:49 - 线程-main，正在写入文件2020-04-05T16:33:54.373
	 * 2020-04-05 16:34:04 [main] INFO  ZeroCopyTest:50 - during time：9869
	 * 2020-04-05 16:34:04 [main] INFO  ZeroCopyTest:63 - 线程-main，正在写入文件2020-04-05T16:34:04.179
	 * 2020-04-05 16:34:04 [main] INFO  ZeroCopyTest:64 - during time：15
	 *
	 * @param args no
	 * @throws IOException read or write file error
	 */
	public static void main(String[] args) throws IOException {
		zeroCopy();
		unZeroCopy();
	}

	/**
	 * This method is potentially much more efficient than a simple loop
	 * that reads from this channel and writes to the target channel.  Many
	 * operating systems can transfer bytes directly from the filesystem cache
	 * to the target channel without actually copying them.
	 * <p>
	 * during time : 15
	 */
	public static void zeroCopy() throws IOException {
		long start = System.currentTimeMillis();
		LocalDateTime now = LocalDateTime.now();
		try (FileChannel destChannel = new RandomAccessFile("target/" + now + ".data", "rw").getChannel();
			 FileChannel srcChannel = new RandomAccessFile("src-db.data", "rw").getChannel()) {
			srcChannel.transferTo(0, srcChannel.size(), destChannel);
			LOG.info("write to file: {}", now);
			LOG.info("during time: {}", (System.currentTimeMillis() - start));
		}

	}

	/**
	 * during time：9869
	 */
	public static void unZeroCopy() throws IOException {
		long start = System.currentTimeMillis();
		LocalDateTime now = LocalDateTime.now();
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File("target/" + now + ".data"));
			 FileInputStream inputStream = new FileInputStream(new File("src-db.data"))) {
			while (inputStream.read() != -1) {
				fileOutputStream.write(inputStream.read());
			}
		}

		LOG.info("write to file: {}", now);
		LOG.info("during time：{}", (System.currentTimeMillis() - start));
	}


}
