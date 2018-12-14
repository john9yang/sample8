package com.sample.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDegrade {

    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        Lock readLock = reentrantReadWriteLock.readLock();
        Lock writeLock = reentrantReadWriteLock.writeLock();


        writeLock.lock();
        readLock.lock();
        writeLock.unlock();
        readLock.unlock();

        System.out.println("程序运行结束");
    }
}
