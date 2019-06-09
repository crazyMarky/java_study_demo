package com.jsd.concurrent.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 虽然原生的线程池好像已经不怎么用了，有更好的框架封装了更好的线程池
         * 但熟悉一下也是不错的，任何线程池的实现都是区别不大的
         * Excutors只是创建线程的一个工厂，具体的线程创建可以查看一下
         * {@link ThreadPoolExecutor}
         * 关于线程池相关的可以去查看一下线程池的详解
         */
        ExecutorService pool = Executors.newCachedThreadPool();
        /**
         * submit用于提交线程，将线程提交到线程池处理
         * 三种submit的方法
         */
        //第一种用于提交一个Runnable接口
        Future<?> submit1 = pool.submit(() -> out(10));
        //可以使用submit1来调用
//        submit1.cancel();
//        submit1.isCancelled();
//        submit1.isDone()
//        submit1.get(); 将会返回null
        System.out.println(submit1.get());
        //提交一个Callable接口
        Future<Integer> submit = pool.submit(() -> op(10));
        System.out.println(submit.get());
        //提交一个Runnable接口,get方法获取第二个值
        Future<String> result = pool.submit(() -> out(10), "result");
        System.out.println(result.get());

        //预定执行的线程池
        //newSingleThreadScheduledExecutor();是只执行单个线程
        //Executors.newScheduledThreadPool(int);是使用int数量的线程执行任务
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //一共有4个定时执行的方法，可以自己看看剩下的api
        //第一个参数是一个Runnable或者Callable接口，第二个是时间，第三个是时间单位
        //在一定时间后执行任务
        ScheduledFuture<Integer> schedule = scheduledExecutorService.schedule(() -> {
            return 10;
        }, 10, TimeUnit.SECONDS);
        System.out.println(schedule.get());
        //一定时间后周期性执行任务(定时任务)
        //第二个参数是初始延迟时间，第三个是周期时间
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("10"), 11, 10, TimeUnit.SECONDS);

    }

    private static void out(int i) {
        //小于0是因为循环输出太多了，影响查看结果
        while (i < 0) {
            System.out.println(i);
            i--;
        }
    }

    private static int op(int i) {
        return ++i;
    }
}
