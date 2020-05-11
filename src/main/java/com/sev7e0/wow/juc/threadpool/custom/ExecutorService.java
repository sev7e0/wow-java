package com.sev7e0.wow.juc.threadpool.custom;


import java.util.List;

/**
 * Title:  ExecutorService.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-11 11:23
 **/

public interface ExecutorService {

	void execute(Runnable runnable);

	void submit(List<Runnable> runnables);

	void destroy();
}
