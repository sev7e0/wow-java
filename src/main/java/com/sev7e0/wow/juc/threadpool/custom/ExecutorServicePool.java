package com.sev7e0.wow.juc.threadpool.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Title:  ExecutorServicePool.java
 * description: 自定义实现线程池
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-11 11:25
 **/

public class ExecutorServicePool implements ExecutorService {
	private static final Logger LOG = LoggerFactory.getLogger(ExecutorServicePool.class);
	//阻塞队列
	private final BlockingQueue<Runnable> blockingQueue;

	//任务计数
	private final AtomicInteger executorCount = new AtomicInteger(0);

	private final Integer poolSize;

	private volatile boolean flag = true;

	public ExecutorServicePool(Integer poolSize, BlockingQueue<Runnable> queue) {
		this.blockingQueue = queue;
		this.poolSize = poolSize;
	}


	@Override
	public void execute(Runnable runnable) {
		int count = executorCount.get();
		if (count<poolSize){
			if (addExecutor(runnable)){
				return;//开始执行任务
			}
		}

		//已经到达最大线程数
		if (blockingQueue.offer(runnable)){
			LOG.info("放入队列成功");
		}else {
			LOG.info("添加任务失败！");
		}
	}

	private boolean addExecutor(Runnable runnable) {
		while (flag){
			int count = executorCount.get();
			if (count > poolSize){
				return false;
			}

			//再次检验是否相同
			if (executorCount.compareAndSet(count, count+1)){
				new Thread(()->{
					Runnable newTask = runnable;
					while (Objects.nonNull(newTask) || (newTask = getNewTask()) != null){
						try {
							newTask.run();
						}finally {
							newTask = null;
						}
					}
				}, String.valueOf(count)).start();
				break;
			}
		}
		return true;
	}

	private Runnable getNewTask(){
		try {
			//会发生阻塞，当队列为空时
			return blockingQueue.take();
		}catch (InterruptedException interruptedException){
			return null;
		}
	}

	@Override
	public void submit(List<Runnable> runnables) {

	}

	@Override
	public void destroy() {
		//先判断队列是否为空

		blockingQueue.clear();

		//结束自旋
		flag = false;

	}
}
