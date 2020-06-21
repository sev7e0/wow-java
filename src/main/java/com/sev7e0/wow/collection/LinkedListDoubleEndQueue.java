package com.sev7e0.wow.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Title:  DoubleTerminalQueue.java
 * description: 双端队列实现
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-06-08 09:00
 **/

public class LinkedListDoubleEndQueue<T> {

	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LinkedListDoubleEndQueue.class);

	public static void main(String[] args) {
		LOG.info("DoubleTerminalQueue");

		LinkedListDoubleEndQueue<String> doubleEndQueue = new LinkedListDoubleEndQueue<>();

		doubleEndQueue.offerFirst("b");
		System.out.println("首部入队b");
		doubleEndQueue.offerLast("c");
		System.out.println("尾部入队c");

		System.out.println("当前队首："+doubleEndQueue.peekFirst());
		System.out.println("当前队尾："+doubleEndQueue.peekLast());

		doubleEndQueue.offerFirst("a");
		System.out.println("首部入队a");

		System.out.println("当前队首："+doubleEndQueue.peekFirst());

		System.out.println("当前队首出队："+doubleEndQueue.popFirst());

		System.out.println("当前队首："+doubleEndQueue.peekFirst());

		System.out.println("当前队尾出队："+doubleEndQueue.popLast());

		System.out.println("当前队尾："+doubleEndQueue.peekLast());

		System.out.println("当前队尾出队："+doubleEndQueue.popLast());

		System.out.println("当前队尾："+doubleEndQueue.peekLast()+doubleEndQueue.peekFirst());

	}

	private Node<T> first;

	private Node<T> last;

	private int size;

	
	public int size() {
		return size;
	}

	
	public boolean isEmpty() {
		return Objects.isNull(first);
	}

	
	public boolean contains(T o) {
		return false;
	}

	/**
	 * 头部入队
	 * @param o
	 * @return
	 */
	public boolean offerFirst(T o) {
		Node<T> tNode = new Node<>();
		tNode.setValue(o);
		tNode.setPre(null);
		tNode.setNext(first);
		if (Objects.isNull(first)){
			last = tNode;
		}
		first = tNode;
		size++;
		return true;
	}

	/**
	 * 尾部入队
	 * @param o
	 * @return
	 */
	public boolean offerLast(T o) {
		Node<T> tNode = new Node<>();
		tNode.setValue(o);
		tNode.setPre(last);
		tNode.setNext(null);
		if (Objects.isNull(last)){
			first = tNode;
		}
		last = tNode;
		size++;
		return true;
	}

	public T popFirst(){
		if (Objects.isNull(first)) return null;
		Node<T> popNode = this.first;
		Node<T> next = this.first.next;
		next.setPre(null);
		this.first = next;
		size--;
		return popNode.value;
	}

	public T popLast(){
		if (Objects.isNull(last)) return null;
		Node<T> popNode = this.last;
		this.last = last.pre;
		size--;
		if (size == 0 ){
			first = null;
		}
		return popNode.value;
	}

	public T peekFirst(){
		if (Objects.isNull(first)) return null;
		return this.first.value;
	}

	public T peekLast(){
		if (Objects.isNull(last)) return null;
		return last.value;
	}




	static class Node<T>{
		private T value;
		private Node<T> pre;
		private Node<T> next;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Node<T> getPre() {
			return pre;
		}

		public void setPre(Node<T> pre) {
			this.pre = pre;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}
	}

}
