package com.carlosfu.tradition.wait_notify.producerconsumer;

public class waitnotify {
	public static void main(String[] args) {
		Queue q = new Queue();
		new Producer(q);
		new Consumer(q);
	}
}





