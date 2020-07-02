package com.sev7e0.wow.pattern.decorator;

/**
 * Title:  FuShiKang.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-21 23:00
 **/

public class FuShiKang implements IProductionIPhone {

	@Override
	public void productionBattery() {
		System.out.println("FuShiKang production the iphone battery");
	}

	@Override
	public void productionScreen() {
		System.out.println("FuShiKang production the iphone screen");
	}
}
