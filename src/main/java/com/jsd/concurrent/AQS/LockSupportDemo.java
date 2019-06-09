package com.jsd.concurrent.AQS;

import java.util.concurrent.locks.LockSupport;

/**
 * 一个LockSupport的demo
 * 主要用于理解LockSupport到底是干什么的，
 * 随后理解AQS的时候就不会有这个疑问了
 *
 * @author A001007008
 */
public class LockSupportDemo {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println("线程开始运作了");
            //park()方法用于阻塞当前线程，当系统有另一个线程unpark的时候，线程就会再次的运作起来
            LockSupport.park();
            System.out.println("阻塞完成了，我又运作了");
        });
        thread.start();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {

        }
        System.out.println("我准备释放thread了");
        //唤醒线程thread
        LockSupport.unpark(thread);
    }
}
