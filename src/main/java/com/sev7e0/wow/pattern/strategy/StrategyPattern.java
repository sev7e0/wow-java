package com.sev7e0.wow.pattern.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  StrategyPattern.java
 * description: 策略模式
 * 优点：
 * 		符合开闭原则，对拓展开放，对修改关闭，只需要添加新的策略即可
 * 		避免使用if else
 * 		使用策略模式可以提高保密性与安全性
 * 缺点：
 * 		客户端需要知道所有策略
 * 		会创建很多的策略类
 *
 * 策略模式通常和其他模式组合使用（工厂模式、单例模式），提升代码整体的质量
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-20 23:17
 **/

public class StrategyPattern {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StrategyPattern.class);

	public static void main(String[] args) {
		LOG.info("StrategyPattern");

	}

}
