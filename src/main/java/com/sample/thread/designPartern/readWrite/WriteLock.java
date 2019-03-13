package com.sample.thread.designPartern.readWrite;

class WriteLock implements Lock{
    private final ReadWriteLockImpl readWriteLock;

    WriteLock( ReadWriteLockImpl readWriteLock ){
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized ( readWriteLock.getMUTEX() )
        {
            try{
                //首先使等待获取写入锁的数字加一
                readWriteLock.incrementWaitingWriters();
                //此时如果有其他线程在进行读操作或者写操作，那么当前线程被挂起
                while( readWriteLock.getReadingReaders() >0 || readWriteLock.getWritingWriters() >0 )
                {
                    readWriteLock.getMUTEX().wait();
                }
            }
            finally {
                //成功获取写锁，使得等待获取写入锁的计数器减一
                this.readWriteLock.decrementWaitingWriters();
            }

            //将正在写入的线程数量加一
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
          synchronized ( readWriteLock.getMUTEX() )
          {
              //减少正在写入锁的线程计数器
              readWriteLock.decrementWritingWriters();
              //将偏好状态修改为false,可以使得读锁被最快速的获得
              //通知唤醒其他在Mutex monitor waitset中的线程
              readWriteLock.getMUTEX().notifyAll();
          }
    }
}
