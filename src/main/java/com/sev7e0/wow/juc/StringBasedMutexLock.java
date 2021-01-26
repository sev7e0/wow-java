package com.sev7e0.wow.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

/**
 * Title:  StringBasedMutexLock.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2021-01-26 23:52
 *
 * 1. 使用ConcurrentHashMap实现锁获取，性能还是不错的;
 * 2. 每个字符串对应一个锁，使用完成后就删除，不会导致内存溢出问题;
 * 3. 可以作为一个外部工具使用，业务代码接入方便，无需像 synchronized 一样，需要整段代码包裹起来;
 * 4. 本文只是想展示实现 String 锁，此锁并不适用于分布式场景下的并发处理;
 * 5. 该锁不支持超时设置，因此上锁后需要自己主动释放，否则将导致没有线程可以拿到锁的情况
 **/

public class StringBasedMutexLock {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StringBasedMutexLock.class);

	public static void main(String[] args) {
		LOG.info("StringBasedMutexLock");
		// 加锁
		StringBasedMutexLock.lock("linkKey");
		// 解锁
		StringBasedMutexLock.unlock("linkKey");
	}

	/**
	 * 字符锁 管理器, 将每个字符串 转换为一个 CountDownLatch
	 *
	 *      即锁只会发生在真正有并发更新 同一个 String 的情况下
	 *
	 */
	private static final ConcurrentMap<String, CountDownLatch> lockKeyHolder = new ConcurrentHashMap<>();

	/**
	 * 基于lockKey 上锁，同步执行
	 *
	 * @param lockKey 字符锁
	 */
	public static void lock(String lockKey) {
		while (!tryLock(lockKey)) {
			try {
				LOG.debug("【字符锁】并发更新锁升级, {}", lockKey);
				blockOnSecondLevelLock(lockKey);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				LOG.error("【字符锁】中断异常:" + lockKey, e);
				break;
			}
		}
	}

	/**
	 * 释放 lockKey 对应的锁选项，使其他线程可执行
	 *
	 * @param lockKey 要使用互斥的字符串
	 * @return true: 释放成功, false: 释放失败，可能被其他线程误释放
	 */
	public static boolean unlock(String lockKey) {
		// 先删除锁，再释放锁，此处会导致后续进来的并发优先执行，无影响
		CountDownLatch realLock = getAndReleaseLock1(lockKey);
		releaseSecondLevelLock(realLock);
		return true;
	}

	/**
	 * 尝试给指定字符串上锁
	 *
	 * @param lockKey 要使用互斥的字符串
	 * @return true: 上锁成功, false: 上锁失败
	 */
	private static boolean tryLock(String lockKey) {
		// 此处会导致大量 ReentrantLock 对象创建吗？
		// 其实不会的，这个数量最大等于外部并发数，只是对 gc 不太友好，会反复创建反复销毁y
		return lockKeyHolder.putIfAbsent(lockKey, new CountDownLatch(1)) == null;
	}

	/**
	 * 释放1级锁（删除） 并返回重量级锁
	 *
	 * @param lockKey 字符锁
	 * @return 真正的锁
	 */
	private static CountDownLatch getAndReleaseLock1(String lockKey) {
		return lockKeyHolder.remove(lockKey);
	}

	/**
	 * 二级锁锁定（锁升级）
	 *
	 * @param lockKey 锁字符串
	 * @throws InterruptedException 中断时抛出异常
	 */
	private static void blockOnSecondLevelLock(String lockKey) throws InterruptedException {
		CountDownLatch realLock = getRealLockByKey(lockKey);
		// 为 null 说明此时锁已被删除，  next race
		if(realLock != null) {
			realLock.await();
		}
	}

	/**
	 * 二级锁解锁（如有必要）
	 *
	 * @param realLock 锁实例
	 */
	private static void releaseSecondLevelLock(CountDownLatch realLock) {
		realLock.countDown();
	}

	/**
	 * 通过key 获取对应的锁实例
	 *
	 * @param lockKey 字符串锁
	 * @return 锁实例
	 */
	private static CountDownLatch getRealLockByKey(String lockKey) {
		return lockKeyHolder.get(lockKey);
	}


}
