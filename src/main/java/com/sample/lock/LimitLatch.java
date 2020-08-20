package com.sample.lock;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class LimitLatch {

    private class Sync extends AbstractQueuedSynchronizer{
        private static final long serialVersionUID = 1L;

        public Sync(){
        }

        @Override
        protected int tryAcquireShared(int arg) {
            long newCount = count.incrementAndGet();
            if ( !released && newCount > limit ){
                //Limit exceeded
                count.decrementAndGet();
                return -1;
            }
            else{
                return 1;
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            count.decrementAndGet();
            return true;
        }
    }

    private final Sync sync;
    private final AtomicLong count;
    private volatile long limit;
    private volatile boolean released = false;

    public LimitLatch(long limit){
        this.limit = limit;
        this.count = new AtomicLong(0);
        this.sync = new Sync();
    }

    public long getCount(){ return count.get();}

    public long getLimit(){ return limit; }

    public void setLimit(long limit){ this.limit = limit; }

    public void countUpOrAwait() throws InterruptedException{
        System.out.println("Counting up["+Thread.currentThread().getName()+"] latch="+getCount());
        sync.acquireSharedInterruptibly(1);
    }

    public long countDown(){
        sync.releaseShared(0);
        long result = getCount();
        System.out.println("Counting down["+Thread.currentThread().getName()+"] latch="+getCount());
        return result;
    }

    public boolean releaseAll(){
        released = true;
        return sync.releaseShared(0);
    }

    public void reset(){
        this.count.set(0);
        released = false;
    }

    public boolean hasQueuedThreads(){ return sync.hasQueuedThreads(); }

    public Collection<Thread> getQueuedThreads(){
        return sync.getQueuedThreads();
    }
}
