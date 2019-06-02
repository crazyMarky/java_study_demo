package com.jsd.concurrent.blocking_queue.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {

    private final ArrayBlockingQueue arrayBlockingQueue;

    public Producer(ArrayBlockingQueue arrayBlockingQueue) {
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    public void produce(int i) throws InterruptedException {
        //将生产的商品放入阻塞队列
        arrayBlockingQueue.put(i);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("poduce: " + i);
            try {
                produce(i);
            } catch (InterruptedException e) {
                System.out.println("发生异常");
            }
        }
    }
}
