package com.sample.queue;

import java.util.concurrent.CountDownLatch;

public class CoutDownLatchDemo {

	public static void main(String[] args) throws Exception{

		CountDownLatch latch = new CountDownLatch(3);
		
		Worker worker1 = new Worker("Jack 程序员1",latch);
		Worker worker2 = new Worker("Rose 程序员2",latch);
		Worker worker3 = new Worker("Json 程序员3",latch);
		
		worker1.start();
		worker2.start();
		worker3.start();
		
		latch.await();
		
		System.out.println("Main thread end!");
	}
	
	static class Worker extends Thread{
		private String workerName;
		private CountDownLatch latch;
		
		public Worker(String workerName, CountDownLatch latch) {
			this.workerName = workerName;
			this.latch = latch;
		}
		
        public void run() {
        	try {
        		System.out.println("Workder:"+workerName+" is begin");
				Thread.sleep(1000);
				System.out.println("Workder:"+workerName+" is end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        	latch.countDown();
        }
	}
}