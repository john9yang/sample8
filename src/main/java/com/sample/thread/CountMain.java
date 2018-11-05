package com.sample.thread;

public class CountMain {

	public static void main(String[] args) {

		Count count = new Count();
		
		for( int i=0;i<5;i++ ) {
			CountAThread ca = new CountAThread(count);
			ca.start();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("5个人干完活：最后的值是："+count.num);
	}

}