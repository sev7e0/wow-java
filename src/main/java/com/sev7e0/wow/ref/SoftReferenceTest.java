package com.sev7e0.wow.ref;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Title:  SoftReferenceTest.java
 * description: 软引用实践
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-13 08:26
 **/

public class SoftReferenceTest {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SoftReferenceTest.class);

	public static void main(String[] args) throws InterruptedException {
		LOG.info("SoftReferenceTest");
		CacheObject cacheObject = CacheObject.getInstance();
		for (int i = 0; i < 100; i++) {
			String key = System.currentTimeMillis()+"=="+ i;
			cacheObject.put(key, new byte[512 * 1024 * 1024] );
			cacheObject.printReference();
			TimeUnit.SECONDS.sleep(1);
		}


	}

}

enum CacheObject{

	CACHE_OBJECT;

	private final ConcurrentHashMap<String, SoftReference<Object>> reference;
	private final ReferenceQueue<Object> referenceQueue;

	CacheObject(){
		this.reference = new ConcurrentHashMap<>();
		this.referenceQueue = new ReferenceQueue<>();
	}


	public static CacheObject getInstance(){
		return CACHE_OBJECT;
	}

	public void put(String key, Object object){
		SoftReference<Object> softReference = new SoftReference<>(object, this.referenceQueue);
		reference.put(key, softReference);
		System.out.println("put success");
	}

	public void printReference(){
		this.reference.forEach((key, value)->{
			System.out.println("key: "+key +"--value: "+value.get());
		});
	}
}