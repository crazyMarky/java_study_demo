package com.jsd.concurrent.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阅读AQS代码需要补充的知识点
 * volatile关键词
 * Interupted状态
 * LockSupport工具
 * <p>
 * 非常标准的AQS锁的实现，
 * 目前实现的是独占模式，也就是传统的锁
 * {@link AbstractQueuedSynchronizer}
 * 附上是AQS类中Demo
 * <pre> {@code
 *  * class Mutex implements Lock, java.io.Serializable {
 *  *
 *  *   // Our internal helper class
 *  *   private static class Sync extends AbstractQueuedSynchronizer {
 *  *     // Reports whether in locked state
 *  *     protected boolean isHeldExclusively() {
 *  *       return getState() == 1;
 *  *     }
 *  *
 *  *     // Acquires the lock if state is zero
 *  *     public boolean tryAcquire(int acquires) {
 *  *       assert acquires == 1; // Otherwise unused
 *  *       if (compareAndSetState(0, 1)) {
 *  *         setExclusiveOwnerThread(Thread.currentThread());
 *  *         return true;
 *  *       }
 *  *       return false;
 *  *     }
 *  *
 *  *     // Releases the lock by setting state to zero
 *  *     protected boolean tryRelease(int releases) {
 *  *       assert releases == 1; // Otherwise unused
 *  *       if (getState() == 0) throw new IllegalMonitorStateException();
 *  *       setExclusiveOwnerThread(null);
 *  *       setState(0);
 *  *       return true;
 *  *     }
 *
 * @author A001007008
 */
public class LockDemo {

    /**
     * 同理可以参考{@link ReentrantLock()}
     * AQS提供的标准独占模式组,tryAcquire-tryRelease
     * 只需要实现这两个方法就可以完成一个标准的锁
     * 用于内部类实现的方法我没有去深究(源码说的是更好的支持，内部类应该是更为private)
     *
     *
     * AQS(独占模式)主要负责干这几件事
     * 1、内部维护一个state表示资源 (getState)
     * 2、一个可以获取资源的方法 (tryAcquire)
     * 3、一个释放资源的方法 (tryRelease)
     * 4、当资源被获取的时候，阻塞其他线程 (acquire)
     */
    class Lock extends AbstractQueuedSynchronizer {
        /**
         * 用于锁住资源
         *
         * @param acquires
         * @return
         */
        @Override
        public boolean tryAcquire(int acquires) {
            //定义当前线程
            final Thread current = Thread.currentThread();
            //获取内部维护的变量
            int state = getState();
            if (state == 0) {
                //当变量为0时未资源没人抢占
                //一个CAS操作用于将变量赋值为传入值
                if (compareAndSetState(0, acquires)) {
                    //设置当前的线程拥有资源
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            //这是一把不可重入的锁，因为我只把值从0-1，如果是可重入的情况下，需要再不是0的情况下做
            //更多的操作可以参考{@link ReentrantLock()}中这部分代码的实现
            return false;
        }

        /**
         * 用于释放资源
         *
         * @param releases
         * @return
         */
        @Override
        protected boolean tryRelease(int releases) {
            //同理只需要恢复变量的值为0就可以将资源释放
            //getState() - releases，会存在线程更新的情况c任然是旧值，但是这个单线程的操作
            //所以不存在这种情况
            int c = getState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (c == 0) {
                free = true;
                //将当前拥有资源的线程设置为null
                setExclusiveOwnerThread(null);
                //赋值state变量
                setState(c);
            }
            return free;
        }
    }

    private Lock lock = new Lock();

    public void lock() {
        /**
         * AQS中的精髓
         *
         * {@code if (!tryAcquire(arg) &&
         *             acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
         *             selfInterrupt();
         *             }
         *  tryAcquire(arg)尝试获取资源 获取到返回true否则为false
         *  addWaiter(Node.EXCLUSIVE)将线程以独占方式加入CLH(双端队列)
         *  acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) 找到线程正确的位置阻塞(正确的位置指的是最后一个正常的线程(不是中断的))
         *  selfInterrupt() 如果在排队中，线程被中断，线程是不会判断是否被中断过的，是在这里补上中断
         */
        lock.acquire(1);
    }

    public void unlock() {
        lock.release(1);
    }

    public boolean tryLock() {
        return lock.tryAcquire(1);
    }


}
