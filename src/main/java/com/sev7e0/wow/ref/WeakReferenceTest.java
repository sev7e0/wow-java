package com.sev7e0.wow.ref;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.WeakHashMap;

/**
 * Title:  WeakReferenceTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-13 08:38
 **/

/**
 * Event handlers are a good use case for weak references. The object that fires events needs a
 * reference to the objects to invoke event handlers on, but you typically don't want the event
 * producer's reference holding to prevent the event consumers from being GC'd. Rather, you'd want
 * the event producer to have a weak reference, and it would then be responsible for checking
 * whether the referenced object was still present.
 **/
public class WeakReferenceTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(WeakReferenceTest.class);

	public static void main(String[] args) throws InterruptedException {
		LOG.info("WeakReferenceTest");
		weakHashMap();
	}

	public static void hashMap() {

	}

	public static void weakHashMap() throws InterruptedException {
		WeakHashMap<Object, Integer> weakHashMap = new WeakHashMap<>();

		Object key = new Object();
		Integer value = 2;

		//stringKey是初始化在常量池中的,系统的字符串常量池会直接记录它，自动保留对它的强引用
		String stringKey = "string";
		//new String生成的string没有在常量池中，也就是说没有多余的强引用，这样在weakHashMap中gc时可以被回收掉
		String string = stringKey + value;
		//调用了intern方法后，jvm回去判断Strings pool中是否包含了该常量，包含则返回引用，不存在则添加到strings pool中再返回reference
		String intern = string.intern();
		weakHashMap.put(key, value);
		weakHashMap.put(stringKey, value);
		weakHashMap.put(string, value);
		weakHashMap.put(intern, value);

		System.out.println(weakHashMap);

		key = null;
		stringKey = null;
//		string = null;
		intern = null;
		System.gc();


		do {
			System.out.println(weakHashMap);
		} while (!weakHashMap.isEmpty());

		System.out.println(weakHashMap);
		/**
		 * 输出：
		 * {java.lang.Object@aecb35a=2}  《======= 此时weakHashMap中有值
		 * [GC (System.gc()) [PSYoungGen: 42106K->5429K(76288K)] 42106K->5509K(251392K), 0.0051139 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
		 * [Full GC (System.gc()) [PSYoungGen: 5429K->0K(76288K)] [ParOldGen: 80K->5033K(175104K)] 5509K->5033K(251392K), [Metaspace: 11431K->11431K(1058816K)], 0.0128648 secs] [Times: user=0.04 sys=0.01, real=0.01 secs]
		 * {}   《======= 此时weakHashMap中无值
		 **/
	}
}
