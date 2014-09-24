package com.carlosfu.tradition.producerconsumer.wait_notify;
public class Consumer implements Runnable {
	Queue q = null;
	
	public Consumer(Queue q) {
		this.q = q;
		(new Thread(this, "Consumer")).start();
	}

	@Override
	public void run() {
		while(q.get()<5){
		}
	}
}