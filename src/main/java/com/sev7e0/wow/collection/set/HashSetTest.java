package com.sev7e0.wow.collection.set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Title:  HashSet.java
 * description: 1. HashSet内部不准有重复元素
 * 2. 最多只允许有一个null
 * <p>
 * 内部使用HashMap实现，与HashMap key属性相同，value使用一个空Object进行占用。
 * 该Object使用static final进行修饰，也就是说该对象为常量，每一个value中都使用
 * 其引用，这样能节省空间。
 * <p>
 * 注意点：
 * 1.非线程安全，可以选择使用{@link java.util.concurrent.CopyOnWriteArraySet}
 * {@link java.util.Collections#synchronizedSet(Set)}
 * {@link java.util.concurrent.ConcurrentSkipListSet}
 * {@link java.util.Collections#newSetFromMap(Map)}
 * 2.HashSet不会维护插入的数据的顺序，可以使用LinkHashSet进行维护。
 * 3.HashSet内部无序，可以使用TreeSet进行排序（注意与第二条不同，二中说的是类似于队列的插入顺序，此描述的是整体顺序）
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-11 08:00
 **/

public class HashSetTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(HashSetTest.class);

	public static void main(String[] args) {
		LOG.info("HashSet");

	}

}
