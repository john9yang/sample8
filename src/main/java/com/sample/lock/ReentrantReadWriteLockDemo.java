package com.sample.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

	public static void main(String[] args) {
        final Count2 ct = new Count2();
        
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

class Count2{
	final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public void get() {
		
		
		lock.readLock().lock();
		System.out.println(Thread.currentThread().getName()+" read begin");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" read end");
		lock.readLock().unlock();
	}
	
	public void put() {
		
		lock.writeLock().lock();
		System.out.println(Thread.currentThread().getName()+" write begin");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" write end");
		lock.writeLock().unlock();		
	}
}