package com.sample.queue;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

	public static void main(String[] args) {
         System.out.println("begin:"+(System.currentTimeMillis()/1000));
         
         final SynchronousQueue<String> sq = new SynchronousQueue<String>();
         
         final Semaphore sem = new Semaphore(1);
         
         for(int i=0;i<10;i++) {
        	 new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						sem.acquire();
						String input = sq.take();
						String output = TestDo.doSome(input);
						System.out.println(Thread.currentThread().getName()+":"+output);
						sem.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}}).start();
         }
         
         for(int i=0;i<10;i++) {
        	 String input = i+"";
        	 try {
				sq.put(input);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
         }
	}

}


class TestDo{
    public static String doSome(String input) {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	String output = input +":"+(System.currentTimeMillis()/1000);
    	
    	return output;
    }
}