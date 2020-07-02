package com.sev7e0.wow.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Title:  ChannelTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-30 08:24
 **/

public class ChannelTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ChannelTest.class);

	public static void main(String[] args) {
		LOG.info("ChannelTest");
		writeFile();
		readFile();
	}

	private static void readFile() {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile("target/test.txt", "rw");
			 FileChannel fileChannel = randomAccessFile.getChannel()) {
			ByteBuffer buffer = ByteBuffer.allocate(64);
			while (fileChannel.read(buffer) != -1) {
				buffer.flip();
				while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get());
				}
				//操作完成清空缓冲区
				buffer.clear();
			}

		} catch (IOException e) {
			LOG.error("error: {}", e.getMessage(), e);
		}
	}

	private static void writeFile() {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile("target/test.txt", "rw");
			 FileChannel fileChannel = randomAccessFile.getChannel()) {
			ByteBuffer buffer = ByteBuffer.allocate(64);
			buffer.put("Channel Test".getBytes());
			buffer.flip();
			fileChannel.write(buffer);
		} catch (IOException e) {
			LOG.error("error: {}", e.getMessage(), e);
		}
	}


}
