package com.sample.thread.designPartern.immutable;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public final class ImmutableIntegerAccumulator {

    private final int init;

    public ImmutableIntegerAccumulator(int init )
    {
        this.init = init;
    }

    public ImmutableIntegerAccumulator(ImmutableIntegerAccumulator accumulator,int init){
        this.init = accumulator.getValue() + init;
    }

    public ImmutableIntegerAccumulator add( int i )
    {
        return new ImmutableIntegerAccumulator(this,i);
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
        ImmutableIntegerAccumulator accumulator = new ImmutableIntegerAccumulator(0);

        IntStream.range(0,10).forEach( i -> new Thread( () -> {
             int inc = 0;
             while ( true ){
                 int oldValue,result;
                 synchronized ( ImmutableIntegerAccumulator.class ) {
                     oldValue = accumulator.getValue();
                     slowly();
                     result = accumulator.add(inc).getValue();
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