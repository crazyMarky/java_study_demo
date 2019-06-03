package com.jsd.concurrent.FutureAndCallable;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author A001007008
 */
public class FutureMain {

    /**
     * Future接口存在以下的几个方法
     * boolean cancel()
     * boolean isCancelled()
     * boolean isDone();
     * V get()
     * V get(long timeout, TimeUnit unit)
     * Future接口设计主要是用于对应获取、取消对应的Callable里面执行的方法返回值、方法
     * FutureTask是一个实现了Runnable和Future的实例，可以将Callable转换成Future和Runnable
     *
     * @param args
     */
    public static void main(String[] args) {
        //模拟地址(我这里使用的是实际的路径)
        //String path = "E:\\java_demo\\java_study_demo\\src\\main\\java\\com\\jsd\\concurrent\\FutureAndCallable\\file";
        ClassLoader classLoader = FutureMain.class.getClassLoader();
        String path1 = classLoader.getResource("").getPath()+"\\concurrent\\FutureAndCallable\\file";
        //模拟关键词
        String keyWord = "a";
        File directory = new File(path1);

        //开启一个Callable的线程，将目录下和关键词传递过去
        CallableDemo callableDemo = new CallableDemo(directory, keyWord);
        //使用FutrueTask获取返回值
        FutureTask<Integer> task = new FutureTask<>(callableDemo);
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println(task.get() + "match files");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
