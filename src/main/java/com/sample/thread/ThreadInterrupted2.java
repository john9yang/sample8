package com.sample.thread;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted2 {

    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                   System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
