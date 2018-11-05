package com.sample.thread;

public class InterruptDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true) {
					if ( Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted!");
						break;
					}
					
					System.out.println(Thread.currentThread().toString());
					Thread.yield();
				}
			}
		};
		
		t1.start();
		Thread.sleep(2000l);
		t1.interrupt();
	}

}
