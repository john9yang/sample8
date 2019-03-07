package com.sample.thread;

import java.util.stream.IntStream;

public class ThreadConstruction {
    private final static String PREFIX = "JOHN-";

    public static void main(String[] args) {
        IntStream.range(0,5).mapToObj(ThreadConstruction::createThread).forEach(Thread::start);
    }

    private static Thread createThread(final int intName){
        return new Thread(() -> System.out.println(Thread.currentThread().getName()),PREFIX+intName);
    }
}
