package com.sev7e0.wow.jvm;

/**
 * Title:  JVMParamTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-10 08:09
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 标配参数：
 * java -version 、java -help
 * <p>
 * X参数：
 * <p>
 * XX参数（*）：
 * boolean类型：-XX:  +(开启)或者-(关闭) 某一个属性值 例如：-XX:-PrintGCDetails -XX:-PrintGCDetails 或者 -XX:-UseSerialGC -XX:+UseSerialGC
 * KV值类型：
 * -XX:MetaspaceSize=128m      -XX:MaxTenuringThreshold=15
 * -XX:InitialHeapSize=128m	-XX:MaxHeapSize=128m
 * 如何查看JVM参数？
 * - jps查看当前进程编号
 * 37728 org.eclipse.lemminx.XMLServerLauncher
 * 20387 sun.tools.jps.Jps
 * 37366 ch.cyberduck.ui.cocoa.MainApplication
 * 88136
 * 88299 org.jetbrains.jps.cmdline.Launcher
 * 88170 org.jetbrains.idea.maven.server.RemoteMavenServer36
 * - jinfo -flag 参数 进程号
 * ➜ jinfo -flag UseSerialGC 88299
 * -XX:-UseSerialGC
 * 关于-Xms 和-Xmx
 * -Xms128m等价于-XX:InitialHeapSize=128m
 * -Xms128m等价于-XX:MaxHeapSize=128m
 * <p>
 * 查看JVM默认值!!!!!!!!!!!!!!!
 * java -XX:+PrintFlagsInitial  初始默认值
 * java -XX:+PrintFlagsFinal	修改后的默认值
 * java -XX:+PrintCommandLineFlags
 * <p>
 * 常用参数
 * -Xms
 * 初始大小内存，默认为物理内存1/64
 * 等价于-XX:InitialHeapSize
 * <p>
 * -Xmx
 * 最大分配内存，默认为物理内存1/4
 * 等价于-XX:MaxHeapSize
 * <p>
 * -Xss
 * 设置单个线程的大小，一般默认为512K~1024K
 * 等价于-XX:ThreadStackSize
 * <p>
 * -Xmn
 * 设置年轻代大小
 * -XX:MetaspaceSize=1024m
 * 设置元空间大小
 * -Xms10m -Xmx10m -XX:MetaspaceSize=1024m -XX:+PrintFlagsFinal
 * <p>
 * -XX:+PrintGCDetails
 * 输出详细GC收集日志信息
 * GC、FullGC
 * <p>
 * -XX:SurvivorRatio=8（意味着Eden占比8）
 * Eden区和Survivor区的占比 8:1:1 也就是整体十份Eden占八份
 * <p>
 * -XX:NewRatio=2（意味着老年代占比2）
 * 配置新生代和老年代的内存占比
 * 老年代2，新生代一直为1，意味着新生代咋占整个堆内存的1/3
 * <p>
 * -XX:MaxTenuringThreshold=（0-15）
 * 设置垃圾最大年龄
 * 每一个对象在新生代的Survivor区交换一次算一次年龄增长，java8 规定最多限制范围在0-15
 */
public class JVMParamTest {
	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(JVMParamTest.class);

	public static void main(String[] args) throws InterruptedException {

		LOG.info("Test for JVM arguments");

		TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

	}
}
