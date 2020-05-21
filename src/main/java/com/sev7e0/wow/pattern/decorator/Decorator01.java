package com.sev7e0.wow.pattern.decorator;

/**
 * 装饰器01，为当前富士康工厂提供生产充电器功能
 */
public class Decorator01 extends FuShiKangDecorator {

	public Decorator01(IProductionIPhone productionIPhone) {
		super(productionIPhone);
	}

	@Override
	public void productionBattery() {
		/**
		 * Decorator抽象类的子类（具体装饰者），里面都有一个构造方法调用super(human),这一句就体现了抽象类依赖于子类实现即抽象依赖于实现的原则
		 */
		super.productionBattery();
		productionCharger();
	}

	@Override
	public void productionScreen() {
		super.productionScreen();
	}

	public void productionCharger(){
		System.out.println("production Charger");
	}
}