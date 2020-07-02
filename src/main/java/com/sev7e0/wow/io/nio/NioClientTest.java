package com.sev7e0.wow.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Title:  NioClientTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-07-01 08:49
 **/

public class NioClientTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(NioClientTest.class);
	private final static String HOST = "localhost";

	private final static Integer PORT = 9999;
	public static void main(String[] args) {
		LOG.info("准备启动NioClient");

		try (SocketChannel channel = SocketChannel.open()){
			final boolean connect = channel.connect(new InetSocketAddress(HOST, PORT));
			if (!connect){
				LOG.error("连接建立失败！");
				return;
			}
			LOG.info("连接服务端成功！");
			ByteBuffer readBuffer = ByteBuffer.allocate(4096);
			ByteBuffer writeBuffer = ByteBuffer.allocate(4096);

			for (int i = 0; i < 5; i++) {
				writeBuffer.put(("你好我是客户端"+i).getBytes());
				writeBuffer.flip();
				channel.write(writeBuffer);
				writeBuffer.clear();
				readBuffer.clear();
				channel.read(readBuffer);
				TimeUnit.SECONDS.sleep(2);
				LOG.info("收到服务端返回的消息：{}", new String(readBuffer.array()));
			}

		}catch (IOException e){
			LOG.error("error message:{}", e.getMessage(), e);
		}catch (InterruptedException e){
			Thread.currentThread().interrupt();
			LOG.info("Thread interrupt:{}",e.getMessage(), e);
		}


	}

}
