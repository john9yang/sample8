package com.sample.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableSample {

	public static void main(String[] args) {
		MyCallable myCallable = new MyCallable();
		FutureTask<String> future = new FutureTask<String>(myCallable);
		new Thread(future).start();
		System.out.println("主线程开始");
		
		try {
			System.out.println("得到的返回结果是:"+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("主线程结束");
	}

}

class MyCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		//模拟做事情
		Thread.sleep(5000);
		System.out.println("这里是Callable线程！");
		return "Shaken,Not Stirred";
	}
	
}