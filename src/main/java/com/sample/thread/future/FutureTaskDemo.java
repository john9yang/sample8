package com.sample.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

	public static void main(String[] args) throws InterruptedException,ExecutionException{
         SonTask task1 = new SonTask("Thread Son1");
         FutureTask<String> f1 = new FutureTask<String>(task1);
         new Thread(f1).start();
         System.out.println(f1.get());//只有得到返回结果后才会继续往线面执行
         
         FutureTask<Integer> f2 = new FutureTask<Integer>(new MyRun(),22);//执行完指定线程,返回指定结果
         new Thread(f2).start();
         System.out.println("result_"+f2.get());//只有得到返回结果后才会继续往线面执行
         
         System.out.println("Thread main end");
	}
}

class SonTask implements Callable<String>{
	private String name="";
	SonTask(String name){
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000l);
		System.out.println(name+"任务计算完成");
		return "result_11";
	}
}

class MyRun implements Runnable{

	@Override
	public void run() {
         try {
			Thread.sleep(1000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
         
        System.out.println("特定线程2完成");
	}
	
}