package com.jsd.concurrent.ThreadPool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * java核心技术中Callable and Future的demo
 * 上次中一个目录启动了一个线程对目录进行检索，显然这是一种非常浪费资源的情况
 * 在需要大量创建短生命周期的线程的时候，可以使用线程池来进行管理
 *
 * @author A001007008
 */
public class CallableDemoFix implements Callable<Integer> {

    /**
     * 封装录入地址
     */
    private File directory;
    /**
     * 封装关键词
     */
    private String keyword;

    /**
     * 计数器
     */
    private int count;

    /**
     * 线程池
     */
    private ExecutorService pool;


    public CallableDemoFix(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    /**
     * Callable接口的核心方法，call()
     * V call() throws Exception;
     * 用于返回V类型的参数，在这个线程开启的查找是可以存在返回值的，
     * Runnable则是无法返回
     *
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        count = 0;
        //获取地址的所有文件
        File[] files = directory.listFiles();
        List<Future<Integer>> results = new ArrayList<>();
        //一个递归调用开启多线程查找目录
        for (File file : files) {
            //用于判断是否目录，如果是目录的情况就需要继续遍历下去，查找文件
            if (file.isDirectory()) {
                //这是我第一次在多线程里面开启多线程
                //在当前目录下寻找文件，如果file为目录的情况，继续递归调用
                CallableDemoFix counter = new CallableDemoFix(file, keyword, pool);
                //原一次目录就生成一个线程
//                FutureTask<Integer> task = new FutureTask<>(counter);
                //使用线程池进行管理线程
                Future<Integer> task = pool.submit(counter);
                results.add(task);
            } else {
                if (search(file)) {
                    count++;
                }
            }
        }
        for (Future<Integer> result :
                results) {
            try {
                count += result.get();
            } catch (ExecutionException e) {

            }
        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file);) {
                boolean found = false;
                while (!found && in.hasNext()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                    }
                }
                return found;
            }
        } catch (IOException e) {
            return false;
        }

    }
}
