package com.sev7e0.wow.pattern.decorator;


/**
 * 装饰器02，为富士康工厂提供组装手机的功能
 */
public class Decorator02 extends FuShiKangDecorator{

	public Decorator02(IProductionIPhone productionIPhone) {
		super(productionIPhone);
	}

	public void assembleIPhone(){
		System.out.println("Started assemble iphone");
	}


	@Override
	public void productionBattery() {
		super.productionBattery();
	}

	@Override
	public void productionScreen() {
		super.productionScreen();
		assembleIPhone();
	}
}