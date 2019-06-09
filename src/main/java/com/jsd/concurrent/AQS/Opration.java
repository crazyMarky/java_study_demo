package com.jsd.concurrent.AQS;

/**
 * 用于测试锁的实现
 *
 * @author A001007008
 */
public class Opration {

    public static int count = 0;

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();
        //新建50条线程，用于测试线程资源争抢情况
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //睡眠10ms保证资源争抢情况出现
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    for (int j = 0; j < 2000; j++) {
                        //使用锁
                        lockDemo.lock();
                        try {
                            count++;
                        } finally {
                            //释放资源
                            lockDemo.unlock();
                        }
                    }

                }
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println(count);
    }
}
