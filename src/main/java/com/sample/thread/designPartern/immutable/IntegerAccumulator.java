package com.sample.thread.designPartern.immutable;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntegerAccumulator {

    private int init;

    public IntegerAccumulator( int init )
    {
        this.init = init;
    }

    public int add( int i )
    {
        this.init += i;
        return this.init;
    }

    public int getValue(){
        return this.init;
    }

    private static void slowly()
    {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntegerAccumulator accumulator = new IntegerAccumulator(0);

        IntStream.range(0,10).forEach( i -> new Thread( () -> {
             int inc = 0;
             while ( true ){
                 int oldValue,result;
                 synchronized ( IntegerAccumulator.class ) {
                     oldValue = accumulator.getValue();
                     slowly();
                     result = accumulator.add(inc);
                 }
                 System.out.println(oldValue+"+"+inc+"="+result);

                 if( inc+oldValue != result ){
                     System.err.println("ERROR:"+oldValue+"+"+inc+"="+result);
                     break;
                 }

                 inc++;
             }
        }
        ).start() );
    }
}