package com.sev7e0.wow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Title:  LruCacheLinkList.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-09-03 00:21
 **/
/*
 * 为最近最少使用（LRU）缓存策略设计一个数据结构，
 * 它应该支持以下操作：获取数据（get）和写入数据（set）。
 * 获取数据get(key)：如果缓存中存在key，则获取其数据值（通常是正数），否则返回-1。
 * 写入数据set(key, value)：如果key还没有在缓存中，则写入其数据值。
 * 当缓存达到上限，它应该在写入新数据之前删除最近最少使用的数据用来腾出空闲位置。
 *
 */

public class LruCacheLinkList<K,V> extends LinkedHashMap<K,V> {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LruCacheLinkList.class);

	public static void main(String[] args) {
		LOG.info("LruCacheLinkList");

	}

		private int cacheSize;

		public LruCacheLinkList(int cacheSize) {
			super(16,0.75f,true);
			this.cacheSize = cacheSize;
		}

		/**
		 * 判断元素个数是否超过缓存容量
		 */
		@Override
		protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
			return size() > cacheSize;
		}
}
