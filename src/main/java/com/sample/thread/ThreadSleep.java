package com.sample.thread;

import java.util.concurrent.TimeUnit;

public class ThreadSleep {

    public static void main(String[] args) {
        new Thread(() ->
        {
            long startTime = System.currentTimeMillis();
            sleep(2_000l);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms",(endTime-startTime)));
        }
        ).start();

        long startTime = System.currentTimeMillis();
        sleep(3_000l);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Main thread total spend %d ms",(endTime-startTime)));
    }

    private static void sleep(long ms){
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}