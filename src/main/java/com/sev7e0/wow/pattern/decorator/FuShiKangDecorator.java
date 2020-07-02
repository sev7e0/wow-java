package com.sev7e0.wow.pattern.decorator;


/**
 * Title:  FuShiKangDecorator.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-21 22:59
 **/

public abstract class FuShiKangDecorator implements IProductionIPhone {

	private final IProductionIPhone productionIPhone;

	public FuShiKangDecorator(IProductionIPhone productionIPhone) {
		this.productionIPhone = productionIPhone;
	}

	@Override
	public void productionBattery() {
		productionIPhone.productionBattery();
	}

	@Override
	public void productionScreen() {
		productionIPhone.productionScreen();
	}
}
