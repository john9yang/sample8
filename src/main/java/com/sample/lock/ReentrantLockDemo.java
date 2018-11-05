package com.sample.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

	public static void main(String[] args) {
        final Count ct = new Count();
        
        for( int i=0;i<2;i++ ) {
        	new Thread() {
        		public void run() {
        			ct.get();
        		}
        	}.start();
        }
        
        for( int i=0;i<2;i++ ) {
        	new Thread() {
        		public void run() {
        			ct.put();
        		}
        	}.start();
        }        
	}

}

class Count{
	final ReentrantLock lock = new ReentrantLock();
	public void get() {
		lock.lock();
		System.out.println(Thread.currentThread().getName()+" get begin");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" get end");
		lock.unlock();
	}
	
	public void put() {
		
		lock.lock();
		System.out.println(Thread.currentThread().getName()+" put begin");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" put end");
		lock.unlock();		
	}
}