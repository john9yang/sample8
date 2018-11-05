package com.sample.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForJoinTaskDemo {

	public static void main(String[] args) throws InterruptedException,ExecutionException{
         ForkJoinPool forJoinPool = new ForkJoinPool();
         CountTask task = new CountTask(1,5);
         Future<Integer> result = forJoinPool.submit(task);
         System.out.println("1-5最终相加的结果:"+result.get());
         
         CountTask task2 = new CountTask(1,100);
         Future<Integer> result2 = forJoinPool.submit(task2);
         System.out.println("1-100最终相加的结果:"+result2.get());
         
         System.out.println("Thread Main End");
	}

}


class CountTask extends RecursiveTask<Integer>{
	private static final long serialVersionUID = 1L;
	private static int splitSize =2 ;
	private int start,end;
	
	public CountTask(int start,int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		
		boolean canCompute = (end-start) <= splitSize;
		if ( canCompute ) {
			for( int i=start;i<=end;i++ ) {
				sum+=i;
			}
		}
		else {
			int middle = (start+end)/2;
			CountTask firstTask = new CountTask(start,middle);
			CountTask secondTask = new CountTask(middle+1,end);
			firstTask.fork();
			secondTask.fork();
			//获得第一个子任务的结果，得不到结果，此线程不会往下面执行
			int firstResult = firstTask.join();
			int secondResult = secondTask.join();
			//合并两个儿子的结果
			sum = firstResult + secondResult;
		}
		return sum;
	}
	
}