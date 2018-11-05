package com.sample.thread;

public class CountAThread extends Thread{

	private Count count;
	
	public CountAThread(Count count) {
		this.count = count;
	}
	
	public void run() {
		count.add();
	}
}
