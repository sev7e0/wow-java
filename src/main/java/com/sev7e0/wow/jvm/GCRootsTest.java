package com.sev7e0.wow.jvm;

/**
 * Title:  GCRootsTest.java
 * description: 关于gc root的分析
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-10 00:26
 **/

/**
 * 什么是GCRoots？
 * GC Roots是一个统称，是所有可以用作“根集可达性算法”中的根。 他们存放在哪里？
 * GC Roots 本身是没有所谓的存储位置，他们都是字节码加载运行过程中加入JVM中的一些普通对象，只不过被认为是GC Roots 。
 * 什么样的对象可以作为gcRoots？
 * - 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）
 * - 方法区中类的静态属性表引用对象
 * - 方法区中常量引用的对象
 * - 本地方法栈中引用的对象
 */
public class GCRootsTest {
	public static void main(String[] args) {

	}
}
