package com.jsd.concurrent.CountDownLatch;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch{@link CountDownLatch()}是一个类似于计数器的工具
 * 里面提供
 * public void await() throws InterruptedException { }; 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { }; 和await()类似，
 * 只不过等待一定的时间后count值还 没变为0的话就会继续执行
 * public void countDown() { };  //将count值减1
 *
 * @author A001007008
 */
public class CountDownLatchDemo {

    /**
     * AQS中共享模式下的具体应用
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        //创建一个启动的门栓
        //构造函数中会将值传递给count
        CountDownLatch startLatch = new CountDownLatch(5);
        //创建一个finish的门栓
        CountDownLatch finishLatch = new CountDownLatch(5);
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        //创建5个线程验证启动时间
        for (int i = 0; i <5 ; i++) {
            executorService.execute(new myRunnable(startLatch,finishLatch));
        }
        //调用方法阻塞当前线程，等所有的start线程都完成了就被唤醒
        startLatch.await();
        //随机做一些事情
        for (int i = 0; i <2000 ; i++) {

        }
        //调用方法阻塞当前线程，等所有的finish线程都完成了就被唤醒
        finishLatch.await();

        //将线程池关闭
        executorService.shutdownNow();
    }
}

class myRunnable implements Runnable {

    CountDownLatch startLatch;

    CountDownLatch finishLatch;

    public myRunnable(CountDownLatch startLatch, CountDownLatch finishLatch) {
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
    }

    @Override
    public void run() {
        //输出时间实验时间是否同一时间启动和结束的
        System.out.println(Thread.currentThread()+"准备启动"+ LocalTime.now());
        //启动门栓计数器-1
        startLatch.countDown();
        System.out.println(Thread.currentThread()+"启动成功"+ LocalTime.now());
        System.out.println(Thread.currentThread()+"准备结束"+ LocalTime.now());
        //结束门栓计数器-1
        finishLatch.countDown();
        System.out.println(Thread.currentThread()+"已经结束"+ LocalTime.now());
    }
}