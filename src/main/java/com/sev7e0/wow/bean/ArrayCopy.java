package com.sev7e0.wow.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Title:  ArrayCopy.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2021-02-21 19:53
 **/

public class ArrayCopy {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ArrayCopy.class);

	public static void main(String[] args) {
		LOG.info("ArrayCopy");
		InnerBean[] oriArray = {new InnerBean("test"), new InnerBean("array"), new InnerBean("copy")};
		InnerBean[] newArray = new InnerBean[3];
		System.arraycopy(oriArray, 0, newArray, 0, oriArray.length);
		LOG.info("原始：");
		LOG.info(Arrays.toString(oriArray));
		LOG.info(Arrays.toString(newArray));
		newArray[0].setName("new");

		LOG.info("修改后：");
		LOG.info(Arrays.toString(oriArray));
		LOG.info(Arrays.toString(newArray));

		newArray[0] = new InnerBean("new2");

		LOG.info("修改后：");
		LOG.info(Arrays.toString(oriArray));
		LOG.info(Arrays.toString(newArray));
		/**
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:25 - ArrayCopy
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:29 - 原始：
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:30 - [InnerBean{name='test'}, InnerBean{name='array'}, InnerBean{name='copy'}]
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:31 - [InnerBean{name='test'}, InnerBean{name='array'}, InnerBean{name='copy'}]
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:34 - 修改后：
		 *
		 * 证明System.arraycopy 是浅拷贝， 当修改新数组内容时对原数据会产生影响
		 *
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:35 - [InnerBean{name='new'}, InnerBean{name='array'}, InnerBean{name='copy'}]
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:36 - [InnerBean{name='new'}, InnerBean{name='array'}, InnerBean{name='copy'}]
		 *
		 * 手动进行新的对象赋值，指向了新的堆地址，所以造成了两个数据不一致
		 *
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:40 - 修改后：
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:41 - [InnerBean{name='new'}, InnerBean{name='array'}, InnerBean{name='copy'}]
		 * 2021-02-21 20:07:47 [main] INFO  ArrayCopy:42 - [InnerBean{name='new2'}, InnerBean{name='array'}, InnerBean{name='copy'}]
		 */

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

		@Override
		public String toString() {
			return "InnerBean{" +
				"name='" + name + '\'' +
				'}';
		}
	}

}
