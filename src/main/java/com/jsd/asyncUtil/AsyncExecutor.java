package com.jsd.asyncUtil;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;



/**
 * 异步任务 执行器
 * @author YellowTail
 * @since 2019-04-24
 */
public class AsyncExecutor {

    
    /**
     * <br>异步任务提交后，都是在这个 链式 阻塞队列里 存着
     */
    private static final LinkedBlockingQueue<Runnable> TASK_WAITING_QUEUE = new LinkedBlockingQueue<>();
    
    /**
     * <br>定长线程池，目前大小为 10
     * <br>数量暂时写死，没有想到什么好的办法支持业务方指定
     */
    private static final ThreadPoolExecutor EXECUTOR_SERVICE = new FanggeekThreadPool(10);
    
    private static final UncaughtExceptionHandler EXCEPTION_HANDLER = new FanggeekThreadExceptionHandler();
    
    /**
     * <br>long 类型的 原子计数器，inc 操作基于 CAS
     */
    private static final AtomicLong TOTAL_COUNT = new AtomicLong();
    
    /**
     * 异步线程 name 前缀
     */
    private static final String AsyncThreadNamePrefix = "async-task-pool-";
    
    /**
     * 异步任务的 ThreadLocal 对象，之所以定义一个，是因为有些线程可能产生了多个异步任务，
     * 需要方便的往下传递这个key
     */
    private static ThreadLocal<String> originalThreadKey = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return null;
        }
    };
    
    private static ThreadLocal<Long> runnableStartTime = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return 0L;
        }
    };
    
    public static void run(Runnable task){
        TOTAL_COUNT.incrementAndGet();
        
        EXECUTOR_SERVICE.execute(new FanggeekRunnable(task));
    }
    
    public static <T> Future<T> submit(Callable<T> task){
        TOTAL_COUNT.incrementAndGet();
        
        return EXECUTOR_SERVICE.submit(task);
    }
    
    /**
     * <br>得到 JVM启动之后，提交到 此执行器的任务总数
     * @return
     * @author YellowTail
     * @since 2019-04-25
     */
    public static long getTotalCount() {
        return TOTAL_COUNT.get();
    }
    
    /**
     * <br>得到当前 在阻塞队列里的任务数量
     * @return
     * @author YellowTail
     * @since 2019-04-25
     */
    public static int getBlockingSize() {
        return TASK_WAITING_QUEUE.size();
    }
    
    /**
     * <br>得到 正在运行的线程池数量
     * <br>请勿频繁调用，会影响性能
     * @return
     * @author YellowTail
     * @since 2019-10-23
     */
    public static int getActiveCount() {
        return EXECUTOR_SERVICE.getActiveCount();
    }
    
    /**
     * <br>得到当前线程池里线程数量总和，包含所有状态
     * <br>请勿频繁调用，会影响性能
     * @return
     * @author YellowTail
     * @since 2019-10-23
     */
    public static int getPoolSize() {
        return EXECUTOR_SERVICE.getPoolSize();
    }
    
    /**
     * <br>得到当前线程池的状态
     * @return
     * @author YellowTail
     * @since 2019-10-23
     */
    public static String getTerminateStatus() {
        if (EXECUTOR_SERVICE.isTerminated()) {
            return "Terminated";
        }
        
        if (EXECUTOR_SERVICE.isTerminating()) {
            return "Terminating";
        }
        
        if (EXECUTOR_SERVICE.isShutdown()) {
            return "Shutdown";
        }
        
        return "normal";
    }
    
    /**
     * <br>当队列堆积严重的时候，尝试对其进行分析
     *
     * @return
     * @author YellowTail
     * @since 2019-11-11
     */
    public static AsyncTaskInfo analysis() {
        return analysis(3);
    }
    
    /**
     * <br>当队列堆积严重的时候，尝试对其进行分析
     *
     * @param number 返回前几名
     * @return
     * @author YellowTail
     * @since 2019-11-11
     */
    public static AsyncTaskInfo analysis(Integer number) {
        if (null == number) {
            number = 3;
        }
        
        long limit = number;
        
        AsyncTaskInfo result = new AsyncTaskInfo();
        
        Map<String, List<FanggeekRunnable>> keyListMap = TASK_WAITING_QUEUE.stream()
                .filter( t -> t instanceof FanggeekRunnable)   //过滤
                .map(t -> FanggeekRunnable.class.cast(t))      //强转
                
                //group 之后收集，是一个map， key 为 group key， value 是 列表
                .collect(Collectors.groupingBy(FanggeekRunnable::getKey));
        
        keyListMap.entrySet()
                .stream()
                //map的value 转成 List 的 size
                .collect(Collectors.toMap(Map.Entry::getKey, t -> t.getValue().size()))
                
                .entrySet()
                .stream()
                //从大到小进行排序， Map.Entry.comparingByValue() 这个是从小到大
                .sorted(Comparator.comparing(Map.Entry::getValue, (k1, k2) ->  (int)( k2 - k1) ) )
                .limit(limit)                         //取前x
                .forEach(k -> {
                    String key = k.getKey();
                    
                    List<AsyncTaskInfo.Info> collect = keyListMap.get(key)
                            .stream()
                            //Map<String, Long>
                            .collect(Collectors.groupingBy(FanggeekRunnable::getTaskName, Collectors.counting()))
                            .entrySet()
                            .stream()
                            //从大到小进行排序， Map.Entry.comparingByValue() 这个是从小到大
                            .sorted(Comparator.comparing(Map.Entry::getValue, (k1, k2) ->  (int)( k2 - k1) ) )
                            .limit(limit)                             //取前x
                            .map(AsyncTaskInfo::fromEntry)
                            .collect(Collectors.toList());
                    
                    AsyncTaskInfo.Info info = new AsyncTaskInfo.Info()
                            .setKey(key)
                            .setCount(k.getValue())
                            .setList(collect);
                    
                    
                    result.getList().add(info);
                });
        
        return result;
    }
    
    public static void setOriginalThreadKey(String value) {
        originalThreadKey.set(value);
    }
    
    /**
     * <br>得到 originalThreadKey
     * <br>不用删除，因为每个 异步任务进来都会设置一次，会覆盖，不删除没有影响
     *
     * @return
     * @author YellowTail
     * @since 2019-11-06
     */
    public static String getOriginalThreadKey() {
        return originalThreadKey.get();
    }
    
    /**
     * <br>线程工厂
     * <br>代码基本照抄jdk，改了一点点
     *
     * @author YellowTail
     * @since 2019-09-26
     */
    static class FanggeekThreadFactory implements ThreadFactory {
        
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        
        FanggeekThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = AsyncThreadNamePrefix +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }
        
        public Thread newThread(Runnable r) {
            String name = namePrefix + threadNumber.getAndIncrement();
            Thread t = new Thread(group, r, name , 0);
            
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            
            t.setUncaughtExceptionHandler(EXCEPTION_HANDLER);
            
            //LOGGER.info("now create a async thread {}", name);
            
            return t;
        }
    }
    
    static class FanggeekThreadExceptionHandler implements UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            //LOGGER.error("AsyncUtils uncaughtException, Thread={}, e ", t, e);
            
            //抛了异常之后，当前线程就死掉了
        }
        
    }
    
    /**
     * <br>自定义的一个简单 线程池
     * <br>主要是 Override 了几个shutdown 操作，在这几个操作里面加了点日志
     *
     * @author YellowTail
     * @since 2019-10-24
     */
    static class FanggeekThreadPool extends ThreadPoolExecutor {
        
        public FanggeekThreadPool(int corePoolSize) {
            this(corePoolSize, corePoolSize,
                    0L, TimeUnit.MILLISECONDS,
                    TASK_WAITING_QUEUE,
                    new FanggeekThreadFactory()      //线程工厂
                    //还有个参数是拒绝策略，直接用默认的策略，抛异常就行了
            );
        }
        
        public FanggeekThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }
        
        @Override
        public void shutdown() {
            //LOGGER.warn("async pool would shutdown now");
            
            super.shutdown();
        }
        
        @Override
        public List<Runnable> shutdownNow() {
            //LOGGER.warn("async pool would shutdown and return Runnable now");
            
            return super.shutdownNow();
        }
        
        @Override
        public void purge() {
            //LOGGER.warn("async pool would purge now");
            
            super.purge();
        }
        
    }
    
    /**
     * <br>自定义 Runnable
     * <br>加了一些属性，会耗内存
     * <br> 属性都是为了分析服务的
     * <br>1. 所以首先是接口url， 但是不知道接口请求时间，因为url可能参数不同，带来的影响不一样
     * <br>2. 接着是 接口请求时间戳， 随后 关键key 格式为 {时间戳}+{url}
     *
     * @author YellowTail
     * @since 2019-11-06
     */
    static class FanggeekRunnable implements Runnable {
        
        static final  AtomicLong ATOMIC_LONG = new AtomicLong(1);
        
        /*
         *              originalThreadId
         *                  |
         *                  |提交
         *                  |
         *                 task1
         *                  |
         *                |提交 |
         *               |       |
         *             task2      task3
         */
        
        /**
         * 具体的任务， 是 lambda 表达式默认实现的 匿名类
         */
        private Runnable task;
        
        /**
         * 关键key 格式为 {接口请求时间戳}+{接口url}, 原因请看{@link FanggeekRunnable}
         */
        private String key;
        
        /**
         * 谁创建了此任务，直接上级
         */
        private String submitorThreadId;
        
        /**
         * 接口url
         */
        private String url;
        
        /**
         * 给每个提交的任务 加一个id
         */
        private long taskId;
        
        /**
         * Runnable 的 class name
         */
        private String taskName;
        
        public FanggeekRunnable(Runnable task) {
            this.task = task;
            
            submitorThreadId = Thread.currentThread().getName();
            
            taskId = ATOMIC_LONG.getAndIncrement();
            
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (int i = 0, len = stackTrace.length; i < len; i++) {
                StackTraceElement s = stackTrace[i];
                
                if (s.toString().startsWith("com.fanggeek.common.utils.thread.AsyncExecutor.run")) {
                    //下一个是各个工程的 AsyncUtils.run 方法
                    //下 下一个就是 提交异步任务的代码行
                    
                    try {
                        taskName = stackTrace[i+2].toString();
                    } catch (Exception e) {
                    }
                    
                    break;
                }
            }
            
            if (null == taskName) {
                //在没有找到的时候，
                taskName = task.getClass().getName();
            }
            
            //得到当前线程的 请求 url， 非请求线程值为null
            //url = ThreadLocalUtils.getUrlThreadLocal();
            url = "ThreadLocalUtils.getUrlThreadLocal()";
            
            //得到当前线程的请求时间戳
            long timeStamp = getStartTimeStamp();
            
            if (! submitorThreadId.startsWith(AsyncThreadNamePrefix)) {
                //不是异步线程，那么就是入口线程（如tomcat线程、apollo线程等）
                key = genKey(timeStamp, url);
            } else {
                //此时是一个异步任务A再次提交一个异步任务B,
                //当前执行线程为A， new出来的对象到时候会被B执行，所以需要记录A的来源
                
                key = AsyncExecutor.getOriginalThreadKey();
            }
            
//            LOGGER.info("new FanggeekRunnable, key {}, submitorThreadId {}, taskId {}, taskName",
//                    key, submitorThreadId, taskId, taskName);
        }
        
        public void run() {
            //此时进入到这里的是, 线程池里的异步线程，originalThreadKey 继续传递
            AsyncExecutor.setOriginalThreadKey(key);
            
            //设置 任务执行开始时间戳
            setStartTimeStamp();
            
//            LOGGER.info("now will invoke, key {}, submitorThreadId {}, taskId {}, taskName",
//                    key, submitorThreadId, taskId, taskName);
            
            try {
                task.run();
            } catch (Exception e) {
                //捕获一下异常，避免 任务和线程同归于尽
//                LOGGER.error("occur bad things, taskId {}, ", taskId, e);
            }
        }
        
        /**
         * <br>设置 任务执行开始时间戳
         *
         * @author YellowTail
         * @since 2019-11-13
         */
        private void setStartTimeStamp() {
            //设置 任务执行开始时间戳
            //ThreadLocalUtils.setTimeStamp 这个变量被 超时切面监控着，不能随意使用，会出错的
            
            runnableStartTime.set(System.currentTimeMillis());
        }
        
        private long getStartTimeStamp() {
            //得到当前线程的请求时间戳
            //long timeStamp = ThreadLocalUtils.getTimeStamp();
            long timeStamp = System.currentTimeMillis();
            
            if (0L == timeStamp) {
                
                //说明不是 http 请求线程
                timeStamp = runnableStartTime.get();
            }
            
            return timeStamp;
        }
        
        public String getKey() {
            return key;
        }
        
        public String getSubmitorThreadId() {
            return submitorThreadId;
        }
        
        public long getTaskId() {
            return taskId;
        }
        
        public String getTaskName() {
            return taskName;
        }
        
        /**
         * <br>生成key
         *
         * @param timeStamp
         * @param url
         * @return
         * @author YellowTail
         * @since 2019-11-11
         */
        private String genKey(long timeStamp, String url) {
            return String.format("%d+%s", timeStamp, url);
        }
    }
    
}
