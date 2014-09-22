package com.carlosfu.tradition.match.wait_notify.v2;

/**
 * 自定义的wait notify
 * 
 * @author leifu
 * @Time 2014年9月22日
 */
public class MyWaitNotify {

	private MonitorObject myMonitorObject = new MonitorObject();

	public void doWait() {
		synchronized (myMonitorObject) {
			try {
				myMonitorObject.wait();
			} catch (InterruptedException e) {

			}
		}
	}

	public void doNotify() {
		synchronized (myMonitorObject) {
			myMonitorObject.notify();
		}
	}
}

class MonitorObject {
}