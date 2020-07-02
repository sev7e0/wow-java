package com.sev7e0.wow.pattern.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Title:  DynamicProxyPattern.java
 * description: 基于jdk的动态代理
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-17 17:08
 **/

/**
 * 接口实现类
 */
public class DynamicProxyPattern {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DynamicProxyPattern.class);

	public static void main(String[] args) {
		LOG.info("DynamicProxyPattern");

		//通过代理创建对象，这其中通过jdk动态搭理生成新的class类文件，并将增强方法写入到类文件中。
		IProduct instance = new ProductDynamicProxy<IProduct>().getInstance(new Product());
		instance.createProduct();

	}
}

/**
 * 被代理接口
 */
interface IProduct {
	void createProduct() throws RuntimeException;
}


class Product implements IProduct {
	private static final Logger LOG = LoggerFactory.getLogger(DynamicProxyPattern.class);

	@Override
	public void createProduct() throws RuntimeException {
		LOG.info("create product!");
		try {
			throw new RuntimeException("e");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


/**
 * 代理类，基于jdk动态代理实现 实现了 {@link InvocationHandler} 接口
 *
 * @param <T>
 */
class ProductDynamicProxy<T> implements InvocationHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ProductDynamicProxy.class);

	private T object;

	public T getInstance(T object) {
		this.object = object;
		Class<?> aClass = object.getClass();
		return (T) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), this);
	}

	/**
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object invoke = new Object();
		try {
			invoke = method.invoke(object, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		after();
		return invoke;
	}

	private void before() {
		LOG.info("代理前置工作");
	}

	private void after() {
		LOG.info("代理后置工作");
	}


}