package com.jsd.concurrent.CountDownLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
 *
 * 以下代码模拟了对某个服务的并发请求，每次只能有 3 个客户端同时访问，请求总数为 10。
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        //每次只能有 3 个客户端同时访问
        final int clientCount = 3;

        //请求总数为 10。
        final int requestCount = 10;

        Semaphore semaphore = new Semaphore(clientCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < requestCount; i++) {
            executorService.execute(() -> {
                try {

                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"--空闲数："+semaphore.availablePermits());
                    Thread.interrupted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }
        System.out.println("最终空闲数："+semaphore.availablePermits());

    }
}
