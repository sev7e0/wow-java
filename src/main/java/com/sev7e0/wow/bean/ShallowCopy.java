package com.sev7e0.wow.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  ShallowCopy.java
 * description: 浅拷贝使用示例
 *
 * @author sev7e0
 * @version 1.0
 * @since 2021-02-21 18:34
 **/

public class ShallowCopy {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ShallowCopy.class);

	public static void main(String[] args) {
		LOG.info("ShallowCopy");

		InnerBean oriBean = new InnerBean("原始名称");

		LOG.info("原始对象名称属性：{}", oriBean.name);

		//新bean
		InnerBean newBean = oriBean;

		LOG.info("新对象名称属性：{}", oriBean.name);

		newBean.setName("新名称");

		LOG.info("修改引用对象属性后：原始对象名称属性：{}", oriBean.name);
		LOG.info("修改引用对象属性后：新对象名称属性：{}", newBean.name);



	}

	static class InnerBean{

		private String name;

		public InnerBean(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
