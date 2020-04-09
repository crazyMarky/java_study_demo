package com.jsd.concurrent.AQS;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock Condition 条件测试
 */
public class ThreadConditionDemo {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();


    static Runnable runnable = () -> {
        try {
            lock.lock();
            System.out.println("Thread is waiting! : " + System.currentTimeMillis());
            condition.await();
            System.out.println("Thread is going on! : " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    };

    //测试
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3000);
        //通知线程reen继续执行
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
