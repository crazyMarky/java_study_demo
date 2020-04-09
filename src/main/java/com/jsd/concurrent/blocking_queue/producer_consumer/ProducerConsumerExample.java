package com.jsd.concurrent.blocking_queue.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用 BlockingQueue 实现生产者消费者问题
 */
public class ProducerConsumerExample {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
    
    

    private static class Producer extends Thread{
        @Override
        public void run() {
            try {
                queue.put("product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("produce..");
        }
    }

    private static class Consumer extends Thread {

        @Override
        public void run() {
            try {
                String product = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consume..");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer();
            producer.start();
            //确保已经生产好了
            producer.join();
        }

        int size = queue.size();
        System.out.println(size);

        for (int i = 0; i < 5; i++) {
            Consumer consumer = new Consumer();
            consumer.start();
        }

        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer();
            producer.start();
        }

    }

}
