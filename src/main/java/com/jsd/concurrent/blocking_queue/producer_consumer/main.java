package com.jsd.concurrent.blocking_queue.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author A001007008
 */
public class main {

    public static void main(String[] args) {
        /**
         * 阻塞队列解决并发资源争抢
         */
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(12);
        Producer producer = new Producer(arrayBlockingQueue);
        Consumer consumer = new Consumer(arrayBlockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
