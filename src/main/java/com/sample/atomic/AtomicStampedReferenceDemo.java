package com.sample.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.stream.IntStream;

public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {
        new AtomicStampedReferenceDemo().recharge();
    }

    public void recharge(){
        AtomicStampedReference<Integer> money = new AtomicStampedReference<>(10,0);

        IntStream.range(0,50).forEach( i -> {
            final int ts = money.getStamp();
            new Thread(()->{
                while( true ){
                    while( true ){
                        Integer m = money.getReference();
                        if ( m < 20 ){
                            System.out.println(Thread.currentThread().getName()+"余额小于20元，余额:"+money.getReference());
                            if ( money.compareAndSet(m,m+20,ts,ts+1) ){
                                System.out.println(Thread.currentThread().getName()+"充值成功,余额:"+money.getReference());
                                break;
                            }
                        }
                        else{
                            break;
                        }

                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
        } );

        new Thread(()->{
            IntStream.range(0,10).forEach( i -> {
                while( true ){
                    final int ts = money.getStamp();
                    Integer m = money.getReference();
                    if ( m > 10 ){
                        System.out.println(Thread.currentThread().getName()+"大于10元");
                        if( money.compareAndSet(m,m-10,ts,ts+1) ){
                            System.out.println(Thread.currentThread().getName()+"成功消费10元，余额:"+money.getReference());
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
