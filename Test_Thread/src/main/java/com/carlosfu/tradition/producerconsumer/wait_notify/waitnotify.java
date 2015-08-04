package com.carlosfu.tradition.producerconsumer.wait_notify;

public class waitnotify {
	public static void main(String[] args) {
		Queue q = new Queue();
		new Producer(q);
		new Consumer(q);
	}
}





