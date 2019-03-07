package com.sample.thread;

public class ThreadGroupDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1");

        ThreadGroup group = new ThreadGroup("TestGroup");
        Thread t2 = new Thread(group,"t2");

        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        System.out.println("Main thread belong group:"+mainThreadGroup.getName());

        System.out.println("t1 and main belong the same group:"+(t1.getThreadGroup() == mainThreadGroup));

        System.out.println("t2 and main belong the same group:"+(t2.getThreadGroup() == mainThreadGroup));

        System.out.println("t2 and main belong the test group:"+(t2.getThreadGroup() == group));
    }
}
