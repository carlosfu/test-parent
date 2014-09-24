package com.carlosfu.tradition.producerconsumer.wait_notify;

public class Producer implements Runnable {
    Queue q = null;

    public Producer(Queue q) {
        this.q = q;
        (new Thread(this, "Producer")).start();
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 5) {
            q.put(i++);
        }
    }
}