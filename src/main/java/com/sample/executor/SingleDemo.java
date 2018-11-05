package com.sample.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleDemo {

	public static void main(String[] args) throws InterruptedException,ExecutionException{
		 ExecutorService executor = Executors.newSingleThreadExecutor();
		 
		 for( int i=0;i<10;i++ ) {
			 final int no = i;
			 Runnable runnable = new Runnable() {

				@Override
				public void run() {
                     try {
                    	System.out.println("into"+no);
						Thread.sleep(1000);
						System.out.println("end"+no);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				 
			 };
			 
			 executor.execute(runnable);
		 }
		 executor.shutdown();
		 System.out.println("Thread Main end!");
	}

}