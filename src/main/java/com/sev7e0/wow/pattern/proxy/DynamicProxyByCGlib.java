package com.sev7e0.wow.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Title:  DynamicProxyByCGlib.java
 * description: 基于CGlib实现的动态代理模式
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-20 21:55
 **/

public class DynamicProxyByCGlib {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DynamicProxyByCGlib.class);

	public static void main(String[] args) {
		LOG.info("DynamicProxyByCGlib");

		final Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Test.class);
		enhancer.setCallback(new TestProxyByCGLib());

		final Test o = (Test)enhancer.create();
		System.out.println(o.echo());

	}

}


class Test{

	public String echo(){
		return "951031";
	}

}

class TestProxyByCGLib implements MethodInterceptor {


	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		before();
		final Object invoke = methodProxy.invokeSuper(o, objects);
		after();
		return invoke;
	}

	private void before(){
		System.out.println("before");
	}

	private void after(){
		System.out.println("after");
	}

	private void Around(){
		System.out.println("around");
	}

}




