package com.jsd.asyncUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步线程池工具
 */
public class AsyncUtils {
    
    private static final ExecutorService EXECUTOR_SERVICE_SINGLE = (ExecutorService) Executors.newSingleThreadExecutor();
    
    private static final ScheduledExecutorService EXECUTOR_SERVICE_SCHEDULED = Executors.newScheduledThreadPool(20);
    
    private static final ExecutorService EXECUTOR_SERVICE_ACTION_LOG = (ExecutorService) Executors.newFixedThreadPool(2);
    
    /**
     * 因为 Runnable 接口是 函数式接口（可以显式的加上@FunctionalInterface， 也可以不加，但是这个接口有且仅有一个方法）
     * <br>所以可以通过 lambda 表达式来快速构造 () -> xxxx
     * @param task
     */
    public static void run(Runnable task){
        AsyncExecutor.run(task);
    }
    
    public static void runActionLog(Runnable task){
        EXECUTOR_SERVICE_ACTION_LOG.execute(task);
    }
    
    /**
     * <br>FIFO 运行任务
     * @param tasks
     * @author YellowTail
     * @since 2019-03-28
     */
    public static void runOneByOne(Runnable... tasks){
        if(tasks != null && tasks.length > 0){
            for(Runnable task : tasks){
                EXECUTOR_SERVICE_SINGLE.execute(task);
            }
        }
    }
    
    public static void runDelay(long delay, TimeUnit unit, Runnable... tasks) {
        if (tasks != null && tasks.length > 0) {
            for (Runnable task : tasks) {
                EXECUTOR_SERVICE_SCHEDULED.schedule(task, delay, unit);
            }
        }
    }
    
}
