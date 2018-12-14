package com.sample.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
public class ListAdd1 {

    private volatile static List list = new ArrayList();


    public void add(){
        list.add("bjsxt");
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        final ListAdd1 list2 = new ListAdd1();
        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //synchronized (lock) {
                        System.out.println("t1启动..");
                        for(int i = 0; i <10; i++){
                            list2.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                            Thread.sleep(500);
                            if(list2.size() == 5){
                                System.out.println("已经发出通知..");
                                //lock.notify();
                                countDownLatch.countDown();
                            }
                        }
                    //}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //synchronized (lock) {
                    System.out.println("t2启动..");
                    if(list2.size() != 5){
                        try {
                            //lock.wait();
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                    throw new RuntimeException();
                //}
            }
        }, "t2");
        t2.start();

        Thread t3=new Thread(()->{
            System.out.println("t3启动..");
            try {
                countDownLatch.await();
                System.out.println("t3收到通知");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3");
        t3.start();

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.start();

    }


}
