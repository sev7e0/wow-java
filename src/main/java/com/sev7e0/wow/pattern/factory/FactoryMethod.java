package com.sev7e0.wow.pattern.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  FactoryMethod.java
 * description: 工厂模式实例
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-26 22:44
 **/

/**
 * 优点:
 * 1:客户端不需要在负责对象的创建,明确了各个类的职责
 * 2:如果有新的对象增加,只需要增加一个具体的类和具体的工厂类即可
 * 3:不会影响已有的代码,后期维护容易,增强系统的扩展性
 *
 * 缺点:
 * 1:需要额外的编写代码,增加了工作量
 */
public class FactoryMethod {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FactoryMethod.class);

	public static void main(String[] args) {
		LOG.info("FactoryMethod");

		FSKFactory appleIphoneFactory = new AppleIphoneFactory();
		FSKFactory appleMacBookFactory = new AppleMacBookFactory();

		Apple iphone = appleIphoneFactory.createAppleModel();
		Apple macBook = appleMacBookFactory.createAppleModel();

		iphone.createApple();
		macBook.createApple();
	}

}

interface FSKFactory{
	Apple createAppleModel();
}

class AppleIphoneFactory implements FSKFactory{

	@Override
	public Apple createAppleModel() {
		System.out.println("生产Iphone模具");
		return new Iphone();
	}
}

class AppleMacBookFactory implements FSKFactory{

	@Override
	public Apple createAppleModel() {
		System.out.println("生产MacBook模具");
		return new MacBook();
	}
}

abstract class Apple{
	abstract void createApple();
}

class Iphone extends Apple{

	@Override
	void createApple() {
		System.out.println("生产Iphone");
	}
}

class MacBook extends Apple{

	@Override
	void createApple() {
		System.out.println("生产MacBook");
	}
}
