package com.sample.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueDemo {

	public static void main(String[] args) throws InterruptedException {

		//final BlockingQueue<String> bq = new ArrayBlockingQueue<String>(16);
		final BlockingQueue<String> bq = new LinkedBlockingQueue<String>(16);
		
		for( int i=0;i<4;i++ ) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+" begin");
					
					while(true) {
						try {
							String log = (String) bq.take();
							parseLog(log);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}}).start();
		}
		
		for(int i=0;i<16;i++) {
			String log = (i+1)+" --> ";
			new Thread( () -> {
				try {
					bq.put(log);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} ).start();
		}
		
		System.out.println("main end!");
	}
	
	
    public static void parseLog(String log) {
    	System.out.println(log + System.currentTimeMillis());
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}