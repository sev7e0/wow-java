package com.sev7e0.wow.pattern;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成器模式（Builder模式、建造者模式）：
 * <p>
 * 模式意图：
 * 将一个复杂对象的构建与他的表示分离，是的同样的构建过程可以创建不同的表示。
 * <p>
 * 使用生成器的几个主要原因:
 * <p>
 * - 在存在了大量可选参数时，静态工厂方法和构造器方式将会变得拖沓，为了不再方法中传递多余的值，你可能需要提供多个
 * 构造器或者静态工厂，相反你将会需要为不需要的设置的参数，传递一个数值。
 * - JavaBean的getset也是一种创建对象的方式，不过构造过程中的对象可能存在不一致的现象。在一些情况下需要你
 * 手动保证线程安全。
 * - 总结以上两个缺点，建造者模式被引入，他不直接创建对象，而是让调用者自己调用必要的静态工厂或者构造器，然后返回
 * builder对象，在他的基础上继续设置属性即可。
 * 建造者模式包含如下角色：
 * Builder：抽象建造者
 * ConcreteBuilder：具体建造者
 * Director：指挥者
 * Product：产品角色
 */

public class BuilderPattern {
	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BuilderPattern.class);

	public static void main(String[] args) {
		Student student = new Student.Builder(11, "lee")
			.age(18)
			.sex("男")
			.builder();
		LOG.info(student.toString());
	}
}

class Student {

	private final int id;
	private final String name;

	//可选参数
	private final int age;
	private final String sex;
	private final String add;
	private final String phone;

	private Student(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.age = builder.age;
		this.sex = builder.sex;
		this.add = builder.add;
		this.phone = builder.phone;
	}

	public static class Builder {
		//必须参数
		private final int id;
		private final String name;

		//可选参数
		private int age;
		private String sex;
		private String add;
		private String phone;

		public Builder(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public Builder sex(String val) {
			this.sex = val;
			return this;
		}

		public Builder add(String val) {
			this.add = val;
			return this;
		}

		public Builder age(int val) {
			this.age = val;
			return this;
		}

		public Builder phone(String val) {
			this.phone = val;
			return this;
		}

		public Student builder() {
			return new Student(this);
		}
	}

	@Override
	public String toString() {
		return this.id + "-" + this.name + "-" + this.age + "-" + this.add + "-" + this.phone + "-" + this.sex;
	}
}
