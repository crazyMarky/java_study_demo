package com.jsd.concurrent.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class CountDownLatchDemo {

    class sync extends AbstractQueuedSynchronizer {

        protected sync(int n) {
            setState(n);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 0) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ; ) {
                int c = getState();
                if (c == 0) {
                    return false;
                } else {
                    int next = c - 1;
                    if (compareAndSetState(c, next)) {
                        return next == 0;
                    }
                }
            }
        }
    }

    private sync sync;

    public CountDownLatchDemo(int n) {
        this.sync = new sync(n);
    }

    public void awit() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    public void countdown() {
        sync.releaseShared(1);
    }

}
