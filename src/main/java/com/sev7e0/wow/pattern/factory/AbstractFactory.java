package com.sev7e0.wow.pattern.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  AbstractFactory.java
 * description: 抽象工厂方法
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-04-26 22:38
 **/

public class AbstractFactory {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AbstractFactory.class);

	public static void main(String[] args) {
		//定义出两个工厂
		AbstractCreator creator1 = new Creator1();
		AbstractCreator creator2 = new Creator2();
		//产生A1对象
		AbstractPhone a1 = creator1.createPhone();
		//产生A2对象
		AbstractPhone a2 = creator2.createPhone();
		//产生B1对象
		AbstractComputer b1 = creator1.createComputer();
		//产生B2对象
		AbstractComputer b2 = creator2.createComputer();

		a1.doSomething();

		a2.doSomething();

		b1.doSomething();

		b2.doSomething();

		a1.shareMethod();
		b2.shareMethod();
	}
}

abstract class AbstractPhone {
	//每个产品共有的方法
	public void shareMethod() {
		System.out.println("Phone类共有方法!");
	}

	//每个产品相同方法，不同实现
	public abstract void doSomething();
}


//产品A1的实现类
class Phone1 extends AbstractPhone {
	public void doSomething() {
		System.out.println("Phone1的实现方法");
	}
}


//产品A2的实现类
class Phone2 extends AbstractPhone {
	public void doSomething() {
		System.out.println("Phone2的实现方法");
	}
}


//抽象产品B类
abstract class AbstractComputer {
	//每个产品共有的方法
	public void shareMethod() {
		System.out.println("Computer类共有方法!");
	}

	//每个产品相同方法，不同实现
	public abstract void doSomething();
}


//产品B1的实现类
class Computer1 extends AbstractComputer {
	public void doSomething() {
		System.out.println("Computer1的实现方法");
	}
}


//产品B2的实现类
class Computer2 extends AbstractComputer {
	public void doSomething() {
		System.out.println("Computer2的实现方法");
	}
}


//抽象工厂类
abstract class AbstractCreator {
	//创建Phone家族
	public abstract AbstractPhone createPhone();

	//创建Computer家族
	public abstract AbstractComputer createComputer();
}


//产品1的实现类
class Creator1 extends AbstractCreator {
	//只生产产品等级为1的A产品
	public AbstractPhone createPhone() {
		System.out.println("创建Phone，等级1");
		return new Phone1();
	}

	//只生产产品等级为1的B产品
	public AbstractComputer createComputer() {
		System.out.println("创建Computer，等级1");
		return new Computer1();
	}
}


//产品等级2的实现类
class Creator2 extends AbstractCreator {
	//只生产产品等级为2的A产品
	public AbstractPhone createPhone() {
		System.out.println("创建Phone，等级2");
		return new Phone2();
	}

	//只生产产品等级为2的B产品
	public AbstractComputer createComputer() {
		System.out.println("创建Computer，等级2");
		return new Computer2();
	}
}

