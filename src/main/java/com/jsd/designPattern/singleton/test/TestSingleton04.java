package com.jsd.designPattern.singleton.test;


import com.jsd.designPattern.singleton.Singleton04;

import java.util.concurrent.*;

public class TestSingleton04 {
    public static void main(String[] args) {
        /**
         * 单线程环境
         */
//        Singleton04 instance = Singleton04.getInstance();
//        Singleton04 instance2 = Singleton04.getInstance();
//
//        System.out.println(instance == instance2);
//        System.out.println(instance);
//        System.out.println(instance2);

        System.out.println("-------------------------------------------------------------------------------");

        /**
         * 多线程环境
         */
        Callable<Singleton04> singleton04Callable  = () -> Singleton04.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton04> submit = executorService.submit(singleton04Callable);
        Future<Singleton04> submit2 = executorService.submit(singleton04Callable);
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
