package com.jsd.concurrent.AQS;

/**
 * 一个关于Interupted的demo
 * 主要用于理解AQS中的中断不响应原理
 *
 * @author A001007008
 */
public class InterruptedDemo {

    /**
     * interrupt是一种线程中断的信号，而不是线程本身的状态
     * 在线程中是不存在线程中断这种状态的
     * interrupt只是将线程中维护的一个变量的值设置成true
     * 线程在阻塞的情况下，是无法响应中断的
     * 会抛出{@code InterruptedException}
     * 以下是出于Java核心技术
     * 如果不加以判定的情况下，只有run方法执行完方法体最后一句语句，并经由return语句返回时
     * 或者出现在方法中没有捕获的异常时线程将终止。没有可以强制线程终止的方法，然而interrupt方法
     * 可以请求终止线程。每个线程都应该不时地检查这个标记，以判断线程是否被终止
     * {@code
     *    while(Thread.interrupted()){
     *        do more work;
     *    }
     * }
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * isInterrupted()，interrupted()都是用于返回一个线程是否被中断过的标记
         * 前者是不清楚这个标记的，如果这个标记被标记为true(线程被中断过)，随后的返回
         * 都为true。
         * 而interrupted()是每次返回之后会清除这部分的标记
         */
        Thread thread = new Thread();
        thread.start();
        thread.interrupt();
        //中断thread线程
        //输出两次中断的判断
        System.out.println("第一次中断判断 " + thread.isInterrupted());
        System.out.println("第二次中断判断 " + thread.isInterrupted());

        //由于interrupted()是Thread里面的方法，所以只能中断当前线程(main线程)
        //中断当前线程(main线程)
        Thread.currentThread().interrupt();
        //输出两次中断的判断
        System.out.println("第一次中断判断 " + Thread.interrupted());
        System.out.println("第二次中断判断 " + Thread.interrupted());

    }
}
