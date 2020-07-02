package com.sev7e0.wow.pattern.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  ObserverPattern
 * description: 观察者模式
 * 本例实现观察者模式以读者订阅公众号为具体案例
 * <p>
 * 有两个公众号 丁香医生（clove）
 * 两名读者 Jobs、Jordan
 * 读者可以订阅公众号，取关公众号，而后公众号每次发布消息，读者都是实时接收到。
 * <p>
 * <p>
 * 观察者模式(对象行为型模式)
 * <p>
 * 定义：对象间一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于他的对象都能得到通知并被
 * 更新。这种模式有时又称作发布-订阅模式、模型-视图模式，它是对象行为型模式。
 * <p>
 * 优点：
 * 降低了目标与观察者的耦合度，两者之间抽象耦合
 * 两者之间建立了触发机制
 * 缺点：
 * 目标与观察者之间的依赖关系并没有完全解除，而且有可能出现循环引用。
 * 当观察者对象很多时，通知的发布会花费很多时间，影响程序的效率。
 * <p>
 * 观察者模式的主要角色如下。
 * 抽象主题（Subject)：也叫抽象目标类，相当于公众号抽象类，它提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法。
 * 具体主题（Concrete Subject)：也叫具体目标类，相当于具体公众号，它实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象。
 * 抽象观察者（Observer)：它是一个抽象类或接口，相当于抽象读者，它包含了一个更新自己的抽象方法，当接到具体主题的更改通知时被调用。
 * 具体观察者（Concrete Observer)：相当于具体哪一个读者，实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态。
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-05-23 14:10
 **/

public class ObserverPattern {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ObserverPattern.class);

	public static void main(String[] args) {
		LOG.info("ObserverPattern");

		//公众号对象
		AbsOfficialAccount account = new CloveOfficialAccount();
		//用户对象
		IUser jobsUser = new JobsUser();
		IUser jordanUser = new JordanUser();

		//订阅操作
		account.follow(jobsUser);
		account.follow(jordanUser);

		//公众号推送更新
		account.pushNews("欢迎关注本公众号");

		//取消关注
		account.unFollow(jobsUser);

		//再次更新推送
		account.pushNews("当前关注用户发生变化");
	}

}
