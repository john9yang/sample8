package com.sample.queue;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) throws Exception{
         final Semaphore semaphore = new Semaphore(3);
         
         for( int i=0;i<10;i++ ) {
        	 final int no = i;
        	 Runnable thread = new Runnable() {

				@Override
				public void run() {
					 try {
                     System.out.println("用户"+no+"连接上了");
                     Thread.sleep(3000);
                     semaphore.acquire();
                     System.out.println("用户"+no+"开始访问后台程序.....");
                     Thread.sleep(1000);
                     semaphore.release();
                     System.out.println("用户"+no+"访问结束");
					 }
					 catch(InterruptedException e) {
						 e.printStackTrace();
					 }
				}
        		 
        	 };
        	 
        	 new Thread(thread).start();
         }
	}

}