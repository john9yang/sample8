package com.sample.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class AtomicReferenceDemo {

    public static void main(String[] args) {
        new AtomicReferenceDemo().recharge();
    }

    public void recharge(){
        AtomicReference<Integer> money = new AtomicReference<>();
        money.set(10);

        IntStream.range(0,3).forEach( i -> {
            new Thread(()->{
                while( true ){
                    while( true ){
                        Integer m = money.get();
                        if ( m < 20 ){
                            System.out.println(Thread.currentThread().getName()+"余额小于20元，余额:"+money.get());
                            if ( money.compareAndSet(m,m+20) ){
                                System.out.println(Thread.currentThread().getName()+"充值成功,余额:"+money.get());
                                break;
                            }
                        }
                        else{
                            break;
                        }

                    }
                }
            }).start();
        } );

        new Thread(()->{
            IntStream.range(0,100).forEach( i -> {
                while( true ){
                    Integer m = money.get();
                    if ( m > 10 ){
                        System.out.println(Thread.currentThread().getName()+"大于10元");
                        if( money.compareAndSet(m,m-10) ){
                            System.out.println(Thread.currentThread().getName()+"成功消费10元，余额:"+money.get());
                            break;
                        }
                        else{
                            System.out.println(Thread.currentThread().getName()+"没有足够的余额");
                            break;
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } );
        }).start();
    }
}
