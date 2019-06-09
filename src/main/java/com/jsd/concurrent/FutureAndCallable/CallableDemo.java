package com.jsd.concurrent.FutureAndCallable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * java核心技术中Callable and Future的demo
 *
 * @author A001007008
 */
public class CallableDemo implements Callable<Integer> {

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

    public CallableDemo(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
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
                CallableDemo counter = new CallableDemo(file, keyword);
                FutureTask<Integer> task = new FutureTask<>(counter);
                results.add(task);
                Thread thread = new Thread(task);
                thread.start();
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
                e.printStackTrace();
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
