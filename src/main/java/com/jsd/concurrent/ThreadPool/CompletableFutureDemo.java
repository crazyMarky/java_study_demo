package com.jsd.concurrent.ThreadPool;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个异步操作
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //开启一个有返回值的异步任务
        CompletableFuture<String> completableFuture
                = CompletableFuture
                //编写任务的实现
                .supplyAsync(() -> UUID.randomUUID().toString())
                //任务完成或抛出异常的回调函数，r为正常的返回值，e为异常类
                .whenComplete((r,e) -> {
                    if (r!=null){
                        System.out.println(r);
                    }
                    if (e != null){
                        e.printStackTrace();
                    }
                });
        //避免主线程直接退出
        TimeUnit.SECONDS.sleep(1);
    }
}
