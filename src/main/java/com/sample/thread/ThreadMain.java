package com.sample.thread;

public class ThreadMain {

	public static void main(String[] args) {

		Thread tA = new ThreadA();
		Thread tB = new ThreadB();
		
		tA.setDaemon(true);
		tB.setDaemon(true);

		tB.start();
		tA.start();
		
		Thread mainThread = Thread.currentThread();
		System.out.println("A是不是守护线程:"+tA.isDaemon());
		System.out.println("B是不是守护线程:"+tB.isDaemon());
		System.out.println("主线程是不是守护线程:"+mainThread.isDaemon());
	}

}