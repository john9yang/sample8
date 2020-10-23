package com.sample.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class TestThread {
    private static String message = "hello";
    private static AtomicReference<String> atomicReference;

    public static void main(String[] args) {
        atomicReference = new AtomicReference<>(message);

        new Thread("T1"){
            @Override
            public void run() {
                atomicReference.compareAndSet(message,"Thread-1");
                message = message.concat("-Thread 1!");
                System.out.println("Message concat is:"+message);
            }
        }.start();

        System.out.println("Message is:"+message);
        System.out.println("Atomic Reference of Message is:"+atomicReference.get());
    }
}
