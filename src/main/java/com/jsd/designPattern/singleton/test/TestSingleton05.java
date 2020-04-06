package com.jsd.designPattern.singleton.test;


import com.jsd.designPattern.singleton.Singleton05;

import java.util.concurrent.*;

public class TestSingleton05 {
    public static void main(String[] args) {
        /**
         * 单线程环境
         */
//        Singleton05 instance = Singleton05.getInstance();
//        Singleton05 instance2 = Singleton05.getInstance();
//
//        System.out.println(instance == instance2);
//        System.out.println(instance);
//        System.out.println(instance2);

        System.out.println("-------------------------------------------------------------------------------");

        /**
         * 多线程环境
         */
        Callable<Singleton05> singleton04Callable  = () -> Singleton05.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton05> submit = executorService.submit(singleton04Callable);
        Future<Singleton05> submit2 = executorService.submit(singleton04Callable);
        try {
            System.out.println(submit.get() == submit2.get());
            System.out.println(submit.get());
            System.out.println(submit2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

    }
}
