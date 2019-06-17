package com.jsd.concurrent.CountDownLatch;

import java.time.LocalTime;
import java.util.concurrent.*;

/**
 * {@link CyclicBarrier}
 * CyclicBarrier主要的功能在于，在并发的情况下，设置一个回环栅栏
 * 当所有需要经过栅栏的线程到达栅栏之后，栅栏才会放行
 * 让线程继续进行下去
 * 相比于CountDownLatch，栅栏是可以复用的，CountDownLatch只能使用一次
 * 在CyclicBarrier可以在线程执行所有线程都到达栅栏之后，指定执行一个动作(随意线程)
 * 同时在使用的时候，CountDownLatch更像是某一线程等待其他线程的执行
 *
 * @author A001007008
 */
public class CyclicBarrierDemo {
    /**
     * 当任意一个栅栏上等待的线程离开了栅栏，栅栏就会被破坏(无效)
     *
     * @param args
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        //在CyclicBarrier可以在线程执行所有线程都到达栅栏之后，指定执行一个动作
//        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(4,()->{
//            System.out.println("123");
//        });
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new myRunnable2(cyclicBarrier));
        }

        //将线程池关闭
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        executorService.shutdownNow();
        //cyclicBarrier是可以在下面中进行重用的
//        for (int i = 0; i < 5; i++) {
//            executorService.execute(new myRunnable2(cyclicBarrier));
//        }
    }
}
class myRunnable2 implements Runnable {

    CyclicBarrier cyclicBarrier;

    public myRunnable2(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        //输出时间实验时间是否同一时间启动和结束的
        System.out.println(Thread.currentThread()+"启动成功"+ LocalTime.now());
        try {
            cyclicBarrier.await();
            //另一种等待，当超时的时候就会不再等待，但是会抛出BrokenBarrierException
//            cyclicBarrier.await(100,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+"我已经完成了");
        System.out.println(Thread.currentThread()+"去做别的事情了");
    }
}