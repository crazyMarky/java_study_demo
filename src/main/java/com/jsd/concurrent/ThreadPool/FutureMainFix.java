package com.jsd.concurrent.ThreadPool;

import com.jsd.concurrent.FutureAndCallable.CallableDemo;

import java.io.File;
import java.util.concurrent.*;

/**
 * @author A001007008
 */
public class FutureMainFix {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //模拟地址(我这里使用的是实际的路径)
        //String path = "E:\\java_demo\\java_study_demo\\src\\main\\java\\com\\jsd\\concurrent\\FutureAndCallable\\file";
        ClassLoader classLoader = FutureMainFix.class.getClassLoader();
        String path1 = classLoader.getResource("").getPath() + "\\concurrent\\FutureAndCallable\\file";
        //模拟关键词
        String keyWord = "a";
        File directory = new File(path1);
        /**
         * Executors是一个线程池的静态工厂类，
         * 里面有
         * newCachedThreadPool();必要时创建新线程；空闲线程会被保留60秒
         * newFixedThreadPool();该池包含固定数量的线程；空闲的线程会一直被保留
         * newScheduledThreadPool();只有一个线程的池，该池顺序执行每一个提交的任务
         * newSingleThreadExecutor()用于预定执行而构建的固定线程池
         */
        ExecutorService pool = Executors.newCachedThreadPool();
        //开启一个Callable的线程，将目录下和关键词传递过去
        CallableDemoFix callableDemo = new CallableDemoFix(directory, keyWord, pool);
        //原使用一次启动一个线程
        //使用FutrueTask获取返回值
//        FutureTask<Integer> task = new FutureTask<>(callableDemo);
        //使用线程池进行管理线程
        Future<Integer> task = pool.submit(callableDemo);
        try {
            System.out.println(task.get() + "match files");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
