package com.sample.thread;

public class Count {
	private byte[] lock = new byte[1];

	public int num = 0;
	
	public void add() {
		synchronized(lock) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		num += 1;
		System.out.println(Thread.currentThread().getName()+":"+num);
		}
	}
}