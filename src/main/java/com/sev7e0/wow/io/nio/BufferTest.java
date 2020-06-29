package com.sev7e0.wow.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;

/**
 * Title:  BufferTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-28 23:39
 **/

public class BufferTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BufferTest.class);

	public static void main(String[] args) throws IOException {
		LOG.info("BufferTest");
		ByteBuffer byteBuffer = ByteBuffer.allocate(32);
		try (SocketChannel open = SocketChannel.open()){
//			open.configureBlocking(false); //设置为非阻塞模式
			//与端口建立连接
			open.connect(new InetSocketAddress("localhost", 9999));
			//写buffer
			open.read(byteBuffer);
			flipTest(byteBuffer,open);
			rewindTest(byteBuffer,open);
		}
	}

	/**
	 * Flips this buffer.  The limit is set to the current position and then
	 * the position is set to zero.  If the mark is defined then it is
	 * discarded.
	 */
	private static void flipTest(ByteBuffer byteBuffer, SocketChannel open) throws IOException {
		while(byteBuffer.hasRemaining()) {
			byteBuffer.flip(); //每一次从buffer读取消息都要调用 flip()
			open.write(byteBuffer);
			byteBuffer.flip(); //每一次从buffer读取消息都要调用 flip()
			open.write(byteBuffer);
			byteBuffer.flip(); //每一次从buffer读取消息都要调用 flip()
			open.write(byteBuffer);
		}
	}
	/**
	 * Rewinds this buffer.  The position is set to zero and the mark is
	 * discarded.
	 *
	 * <p> Invoke this method before a sequence of channel-write or <i>get</i>
	 * operations, assuming that the limit has already been set
	 * appropriately.  For example:
	 *
	 * <blockquote><pre>
	 * out.write(buf);    // Write remaining data
	 * buf.rewind();      // Rewind buffer
	 * buf.get(array);    // Copy data into array</pre></blockquote>
	 */
	private static void rewindTest(ByteBuffer byteBuffer, SocketChannel open) throws IOException {
		byteBuffer.flip();//在调用rewind之前，还是需要先调用flip，以此来设置limit = position，之后保持limit不变
		while(byteBuffer.hasRemaining()) {
			byteBuffer.rewind(); // 把position置0 limit不变，使得实现重复读取
			open.write(byteBuffer);
			byteBuffer.rewind();
			open.write(byteBuffer);
			byteBuffer.rewind();
			open.write(byteBuffer);
		}
	}

}
