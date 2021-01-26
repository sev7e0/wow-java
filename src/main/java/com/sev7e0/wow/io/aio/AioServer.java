package com.sev7e0.wow.io.aio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Title:  AioServer.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-07-03 08:27
 **/

public class AioServer {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AioServer.class);

	private String hostname = "localhost";
	private Integer port = 9999;

	public AioServer(String hostname, Integer port) {
		this.hostname = hostname;
		this.port = port;
	}

	public static void main(String[] args) {
		LOG.info("AioServer");

	}

	private void startListen(){

		try (final AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open()){

			channel.bind(new InetSocketAddress(hostname, port));
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4096);
			channel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
				@Override
				public void completed(AsynchronousSocketChannel result, Object attachment) {

				}

				@Override
				public void failed(Throwable exc, Object attachment) {

				}
			});

		}catch (IOException e){
			LOG.error("I/O Exception: {}", e.getMessage(), e);
		}
	}

}
