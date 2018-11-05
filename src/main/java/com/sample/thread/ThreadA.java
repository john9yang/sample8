package com.sample.thread;

public class ThreadA extends Thread{

	public void run() {
		for(long i=0;i < 9999999l;i++) {
			System.out.println("后台线程A第"+i+"次执行");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
