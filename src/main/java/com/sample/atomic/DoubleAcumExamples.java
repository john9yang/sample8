package com.sample.atomic;

import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.function.DoubleBinaryOperator;

public class DoubleAcumExamples
{

    public static void main( String[] args )
    {
        DoubleBinaryOperator operator = new DoubleBinaryOperator()
        {

            @Override
            public double applyAsDouble( double x, double y )
            {
                return x + y;
            }
        };

        double identity = 1.0;
        DoubleAccumulator doubleAcum = new DoubleAccumulator( operator, identity );

        //equivalent using lambdas
        DoubleAccumulator doubleAcumLambda = new DoubleAccumulator( (x,y)->x+y, identity);

        //several get methods
        System.out.println( "get " + doubleAcum.get() );

        //with different castings
        System.out.println( "byteValue " + doubleAcum.byteValue() );

        System.out.println( "doubleValue " + doubleAcum.doubleValue() );

        System.out.println( "floatValue " + doubleAcum.floatValue() );

        System.out.println( "intValue " + doubleAcum.intValue() );

        //reset to identity after get
        System.out.println( "get then reset " + doubleAcum.getThenReset() );

        System.out.println( "get " + doubleAcum.get() );

        //accumulates the value
        doubleAcum.accumulate( 80.9 );

        System.out.println( "get " + doubleAcum.get() );

        //resets again
        doubleAcum.reset();

        System.out.println( "get after reset " + doubleAcum.get());
    }
}

