package lock;

import com.sample.lock.LimitLatch;
import org.junit.Assert;
import org.junit.Test;

public class LimitLatchTest {
    private static final long THREAD_WAIT_TIME = Long.MAX_VALUE;

    @Test
    public void testNoThreads() throws Exception{
        LimitLatch latch = new LimitLatch(0);
        Assert.assertFalse("No thread should be waiting",latch.hasQueuedThreads());
    }

    @Test
    public void testOneThreadNoWait() throws Exception {
        LimitLatch latch = new LimitLatch(1);
        Object lock = new Object();
        checkWaitingThreadCount(latch, 0);
        TestThread testThread = new TestThread(latch, lock);
        testThread.start();
        if (!waitForThreadToStart(testThread)) {
            Assert.fail("Test thread did not start");
        }
        checkWaitingThreadCount(latch, 0);
        if (!waitForThreadToStop(testThread, lock)) {
            Assert.fail("Test thread did not stop");
        }
        checkWaitingThreadCount(latch, 0);
    }

    @Test
    public void testOneThreadWaitCountDown() throws Exception {
        LimitLatch latch = new LimitLatch(1);
        Object lock = new Object();
        checkWaitingThreadCount(latch, 0);
        TestThread testThread = new TestThread(latch, lock);
        latch.countUpOrAwait();
        testThread.start();
        if (!waitForThreadToStart(testThread)) {
            Assert.fail("Test thread did not start");
        }
        checkWaitingThreadCount(latch, 1);
        latch.countDown();
        if (!waitForThreadToStop(testThread, lock)) {
            Assert.fail("Test thread did not stop");
        }
        checkWaitingThreadCount(latch, 0);
    }

    private void checkWaitingThreadCount(LimitLatch latch, int target) throws InterruptedException {
        long wait = 0;
        while (latch.getQueuedThreads().size() != target && wait < THREAD_WAIT_TIME) {
            Thread.sleep(100);
            wait += 100;
        }
        Assert.assertEquals(target,  latch.getQueuedThreads().size());
    }

    private static class TestThread extends Thread {

        private final Object lock;
        private final LimitLatch latch;
        private volatile int stage = 0;

        public TestThread(LimitLatch latch, Object lock) {
            this.latch = latch;
            this.lock = lock;
        }

        public int getStage() {
            return stage;
        }

        @Override
        public void run() {
            try {
                stage = 1;
                latch.countUpOrAwait();
                stage = 2;
                if (lock != null) {
                    synchronized (lock) {
                        lock.wait();
                    }
                }
                latch.countDown();
                stage = 3;
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
        }
    }

    private boolean waitForThreadToStart(TestThread t) throws InterruptedException {
        long wait = 0;
        while (t.getStage() == 0 && wait < THREAD_WAIT_TIME) {
            Thread.sleep(100);
            wait += 100;
        }
        return t.getStage() > 0;
    }

    private boolean waitForThreadToStop(TestThread t, Object lock) throws InterruptedException {
        long wait = 0;
        while (t.getStage() < 3 && wait < THREAD_WAIT_TIME) {
            Thread.sleep(100);
            wait += 100;
            synchronized (lock) {
                lock.notifyAll();
            }
        }
        return t.getStage() == 3;
    }
}
