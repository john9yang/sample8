package com.sample.queue;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {

		CyclicBarrier barrier = new CyclicBarrier(3,new TotalTask());
		BillTask worker1 = new BillTask("111",barrier);
		BillTask worker2 = new BillTask("222",barrier);
		BillTask worker3 = new BillTask("333",barrier);
		
		worker1.start();
		worker2.start();
		//worker3.start();
		
		System.out.println("Main thread end!");
	}
	
	
	static class TotalTask extends Thread{
		public void run() {
			System.out.println("所有子任务都执行完了,就开始执行主任务了。");
		}
	}

	static class BillTask extends Thread{
		private String billName;
		private CyclicBarrier barrier;
		
		public BillTask(String billName, CyclicBarrier barrier) {
			this.billName = billName;
			this.barrier = barrier;
		}
		
        public void run() {
        	try {
        		System.out.println("市区:"+billName+"运算开始: ");
				Thread.sleep(1000);
				System.out.println("市区:"+billName+"运算完成,等待中...");
				barrier.await();
				System.out.println("全部都结束，市区"+billName+" 才开始后面的工作");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
        }
	}
}


