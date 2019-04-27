package com.sample.atomic;

import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * we do not have to take care of concurrency problems and read write access locks, everything is
 * done in the accumulator, so it is very easy to share variables between threads
 *
 * @author dgutierrez-diez
 */
public class DoubleAcumApplication
{

    public static void main( String[] args )
    {
        // this variable is used by several threads (10)
        DoubleAccumulator doubleAcum = new DoubleAccumulator( ( x, y ) -> x + y, 1.0 );

        for( int i = 0; i < 10; i++ )
        {
            Runnable runnable = new Runnable()
            {

                @Override
                public void run()
                {
                    for( int j = 0; j < 100; j++ )
                    {
                        double value = doubleAcum.get();
                        System.out.println( Thread.currentThread().getName() + " value begin loop " + value );
                        if( value < 75 )
                        {
                            // each runnable accumulates random value
                            double random = Math.random() * 100;
                            System.out.println( Thread.currentThread().getName() + " accumulating random " + random );
                            doubleAcum.accumulate( random );
                            System.out.println( Thread.currentThread().getName() + " value after accumulating "
                                    + doubleAcum.get() );
                        }
                        else
                        {
                            System.out.println( Thread.currentThread().getName() + " reseting " );
                            doubleAcum.reset();
                            System.out.println( Thread.currentThread().getName() + " value after reseting "
                                    + doubleAcum.get() );
                        }
                    }

                }
            };
            Thread t = new Thread( runnable, Integer.toString( i ) );

            t.start();
        }
    }
}

