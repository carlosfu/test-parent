package com.carlosfu.tradition.wait_notify.producerconsumer;
public class Queue {
	int n;
	boolean valueSet = false;
	
	public synchronized int get() {
		if(!valueSet) {		// if valueSet == false,wait else try to got value
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		System.out.println("Get n : " + n);
		valueSet = false;
		notify();
		
		return n;
	}
	
	public synchronized void put(int n) {
		if(valueSet) {		// if valueSet == true,already have value so wait fetch,else put 
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.n = n;
		System.out.println("Put n : " + n);
		valueSet = true;
		notify();
	}
}