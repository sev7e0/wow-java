package com.sev7e0.wow.innerclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  AnonymityInnerClass.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-07-13 16:42
 **/

public class AnonymityInnerClass {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AnonymityInnerClass.class);

	public static void main(String[] args) {
		LOG.info("AnonymityInnerClass");

		final AnonymityInnerInterface anonymityInnerClass = new AnonymityInnerInterface() {
			@Override
			public void get() {
				System.out.println("AnonymityInner");
			}
		};
		final AnonymityInnerInterface anonymityInnerClass1 = () -> System.out.println("AnonymityInner");


		anonymityInnerClass1.get();

		anonymityInnerClass.get();

	}

}

interface AnonymityInnerInterface{

	public void get();
}