package com.jsd.concurrent.FutureAndCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {
            int result = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(10);
                result += i;
            }
            return result;
        });

        Thread thread = new Thread(integerFutureTask);
        thread.start();

        System.out.println(integerFutureTask.get());
    }
}
