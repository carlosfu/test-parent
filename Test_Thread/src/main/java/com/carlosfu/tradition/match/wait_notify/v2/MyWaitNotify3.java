package com.carlosfu.tradition.match.wait_notify.v2;

/**
 * 由于莫名其妙的原因，线程有可能在没有调用过notify()和notifyAll()的情况下醒来。这就是所谓的假唤醒（spurious
 * wakeups）。无端端地醒过来了。
 * 
 * @author leifu
 * @Time 2014-9-23
 */
public class MyWaitNotify3 {
	MonitorObject myMonitorObject = new MonitorObject();
	boolean wasSignalled = false;

	public void doWait() {
		synchronized (myMonitorObject) {
			while (!wasSignalled) {
				try {
					myMonitorObject.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// clear signal and continue running.
			wasSignalled = false;
		}
	}

	public void doNotify() {
		synchronized (myMonitorObject) {
			wasSignalled = true;
			myMonitorObject.notify();
		}
	}
}
