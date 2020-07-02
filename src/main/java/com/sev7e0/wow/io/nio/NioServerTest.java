package com.sev7e0.wow.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Title:  SelectorTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-07-01 08:16
 **/

public class NioServerTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(NioServerTest.class);

	private final static String HOST = "localhost";

	private final static Integer PORT = 9999;

	private static volatile String clientMessage;

	public static void main(String[] args) {
		LOG.info("准备启动NioServer");

		try (Selector selector = Selector.open();
			 ServerSocketChannel channel = ServerSocketChannel.open()) {
			prepare(selector, channel);
			ByteBuffer readBuffer = ByteBuffer.allocate(4096);
			ByteBuffer writeBuffer = ByteBuffer.allocate(40960);


			while (true) {
				int select = selector.select(1000);
				//如果没有准备好的事件，进入下一次循环
				if (select == 0) {
					continue;
				}
				Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
				while (keyIterator.hasNext()) {
					/**
					 * 每次使用Selector注册Channel时，都会创建一个SelectionKey。key在通过调用其cancel方法、关闭其channel或关闭其Selector来取消之前保持有效。
					 *
					 * 取消一个key并不会立即将其从selector中删除；而是将其添加到selector的“已取消”键集中，以便在下一次选择操作中删除。
					 * 可以通过调用key的isValid方法来测试密钥的有效性。 SelectionKey包含两个表示为整数值的操作集。
					 *
					 * operation sets的每一位表示SelectionKey的Channel支持的一类可选操作。
					 *
					 * interest sets确定在下次调用selector的选择方法之一时将测试哪些操作类别是否就绪。interest sets是用创建密钥时给定的值初始化的；
					 * 可以通过interestOps（int）方法更改它。
					 *
					 * ready sets标识操作类别，key的Selector已检测到key的channel已准备就绪。在创建密钥时，ready sets被初始化为零；
					 * 稍后在选择操作期间，selector可以更新它，但不能直接更新它。 SelectionKey的ready set表示它的channel已经为某个操作类别做好了准备，
					 * 这是一个提示，但不能保证这样一个类别中的操作可以由线程执行而不会导致线程阻塞。
					 *
					 * 在选择操作完成后，ready sets最有可能是精确的。外部事件和在相应channel上调用的I/O操作可能会使其不准确。
					 * 此类定义了所有已知的操作集位，但给定channel支持哪些位取决于channel的类型。SelectableChannel的每个子类都定义一个validOps（）方法，
					 * 该方法返回一个集合，该集合只标识channel支持的那些操作。尝试设置或测试键的channel不支持的操作集位将导致相应的运行时异常。
					 *
					 * 通常有必要将某些特定于应用程序的数据与SelectionKey相关联，例如表示高级协议状态并处理就绪通知的对象，以便实现该协议。
					 * 因此，SelectionKey支持将单个任意对象附加到键。对象可以通过attach方法附加，然后通过attachment方法检索。
					 *
					 * 多个并发线程可以安全地使用SelectionKey。读写interest sets的操作通常与selector的某些操作同步。
					 * 这种同步的具体执行方式取决于实现：在简单的实现中，如果选择操作已经在进行中，那么读取或写入兴趣
					 * 集可能会无限期地阻塞；在高性能的实现中，读取或写入interest sets可能会短暂地阻塞（如果有的话）。
					 * 在任何情况下，选择操作将始终使用操作开始时的当前interest sets值。
					 */
					SelectionKey selectionKey = keyIterator.next();
					if (selectionKey.isAcceptable()) {
						accept(selector, channel);
					} else if (selectionKey.isConnectable()){
						LOG.info("isConnectable");
					} else if (selectionKey.isReadable()) {
						read(readBuffer, selectionKey);
					} else if (selectionKey.isWritable()) {
						write(writeBuffer, selectionKey);
					}
					keyIterator.remove();
				}
			}
		} catch (IOException e) {
			LOG.error("error message: {}", e.getMessage(), e);
		}
	}

	private static void prepare(Selector selector, ServerSocketChannel channel) throws IOException {
		channel.bind(new InetSocketAddress(HOST, PORT));
		LOG.info("服务端启动端口为：{}开始监听。", PORT);
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_ACCEPT);
	}

	private static void write(ByteBuffer writeBuffer, SelectionKey selectionKey) throws IOException {
		writeBuffer.clear();
		writeBuffer.put(("服务端已经收到消息！消息内容为:"+clientMessage).getBytes());
		writeBuffer.flip();
		SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
		socketChannel.write(writeBuffer);
		selectionKey.interestOps(SelectionKey.OP_READ);
	}

	private static void read(ByteBuffer readBuffer, SelectionKey selectionKey) throws IOException {
		SocketChannel readChannel = (SocketChannel) selectionKey.channel();
		readBuffer.clear();
		readChannel.read(readBuffer);
		readBuffer.flip();
		clientMessage = new String(readBuffer.array());
		LOG.info("服务端接收道消息：{}", clientMessage);
		selectionKey.interestOps(SelectionKey.OP_WRITE);
	}

	private static void accept(Selector selector, ServerSocketChannel channel) throws IOException {
		SocketChannel accept = channel.accept();
		accept.configureBlocking(false);
		accept.register(selector, SelectionKey.OP_READ);
	}

}
