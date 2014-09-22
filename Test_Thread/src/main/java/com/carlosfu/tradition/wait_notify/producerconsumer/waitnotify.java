package com.carlosfu.tradition.wait_notify.producerconsumer;

public class waitnotify {
	public static void main(String[] args) {
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
	}
}





