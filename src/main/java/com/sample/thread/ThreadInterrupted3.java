package com.sample.thread;


import java.util.concurrent.TimeUnit;

public class ThreadInterrupted3 {

    public static void main(String[] args){
        //1.判断当前线程是否被中断
        System.out.println("Main thread is interrupted?"+Thread.interrupted());

        //2.中断当前线程
        Thread.currentThread().interrupt();

        //3.判断当前线程是否已经被中断
        System.out.println("Main thread is interrupted?"+Thread.currentThread().isInterrupted());

        //4.当前线程执行可中断方法
        try{
            TimeUnit.MINUTES.sleep(1);
        }
        catch(InterruptedException e){
            //5捕获中断信号
            System.out.println("I will be interrupted still.");
        }
    }
}
