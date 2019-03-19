package com.sample.thread.designPartern.threadContext;

import java.util.concurrent.TimeUnit;

public class ThreadLocalMemoryLeak {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();

        TimeUnit.SECONDS.sleep(30);

        threadLocal.set(new byte[1024*1024*100]);  // 100Mb
        threadLocal.set(new byte[1024*1024*100]);  // 100Mb
        threadLocal.set(new byte[1024*1024*100]);  // 100Mb

        threadLocal = null;

        Thread.currentThread().join();
    }
}