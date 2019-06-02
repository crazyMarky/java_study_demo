package com.jsd.concurrent.blocking_queue.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {

    private final ArrayBlockingQueue arrayBlockingQueue;

    public Consumer(ArrayBlockingQueue arrayBlockingQueue) {
        this.arrayBlockingQueue =arrayBlockingQueue;
    }

    public void consumer() throws InterruptedException {
        //取出阻塞队列的第一个资源
        arrayBlockingQueue.take();
    }

    @Override
    public void run() {
       while (true){
           try {
               System.out.println("拿出了" +arrayBlockingQueue.take());
               consumer();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
