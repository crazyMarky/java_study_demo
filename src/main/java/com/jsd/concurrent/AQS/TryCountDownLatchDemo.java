package com.jsd.concurrent.AQS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TryCountDownLatchDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo(5);
        for (int i = 0; i <5; i++) {
            executorService.execute(new myRunner(countDownLatchDemo));
        }
    }
}

class myRunner implements Runnable {
    CountDownLatchDemo countDownLatchDemo;

    public myRunner(CountDownLatchDemo countDownLatchDemo) {
        this.countDownLatchDemo = countDownLatchDemo;
    }

    @Override
    public void run() {
        countDownLatchDemo.countdown();
        System.out.println(Thread.currentThread()+"开始了" + LocalTime.now());
    }
}