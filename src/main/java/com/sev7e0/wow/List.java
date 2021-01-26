package com.sev7e0.wow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title:  List.java
 * description: TODO
 *
 * @author sev7e0
 * @version 1.0
 * @since 2020-09-09 20:43
 **/

public class List {



	/**
	 * logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(List.class);

	public static void main(String[] args) {


	}

	private int hasC(Node node){

		Node slow;
		Node fast;
		while (node != null && node.getNext()!=null){
			slow = node.getNext();
			fast = node.getNext().getNext();
//			if ()
		}

		return 0;
	}

}

class Node{
	private Node next;
	private int value;

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
