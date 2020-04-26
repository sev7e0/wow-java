package com.sev7e0.wow.hash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Random;

/**
 * Title:  BloomFilterTest.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-12 23:29
 **/

public class BloomFilterTest {
	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BloomFilterTest.class);
	//过滤器的bit数组大小
	static int sizeOfNumberSet = 50000000;
	//模拟产生随机数
	static Random generator = new Random();

	public static void main(String[] args) {
		//统计误判次数
		int error = 0;
		//使用一个hashSet用来准确去重，这样就是计算误判率
		HashSet<Integer> hashSet = new HashSet<>();
		//创建过滤器
		BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), sizeOfNumberSet);
		for (int i = 0; i < sizeOfNumberSet; i++) {
			int number = generator.nextInt();
			//当过滤器与HashSet判断结果不同时发生误判，error+1
			if (filter.mightContain(number) != hashSet.contains(number)) {
				error++;
			}
			filter.put(number);
			hashSet.add(number);
		}
		LOG.info("Error count: " + error + ", error rate = " + String.format("%f", (float) error / (float) sizeOfNumberSet));
	}
}