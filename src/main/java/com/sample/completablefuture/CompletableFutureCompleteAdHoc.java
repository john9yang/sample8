package com.sample.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureCompleteAdHoc
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException
    {

        // creates one completable that is not going to end
        CompletableFuture<Integer> completableFutureToBeCompleted2 = CompletableFuture.supplyAsync( ( ) -> {
            for( int i = 0; i < 10; i-- )
            {
                System.out.println( "i " + i );
            }
            return 10;
        } );

        // use static because we are in a static method
        CompletableFuture<Integer> completor = CompletableFuture.supplyAsync( ( ) -> {
            System.out.println( "completing the other" );
            completableFutureToBeCompleted2.complete( 222 );
            return 10;
        } );

        // get its result and completes the other
        System.out.println("completor value " +  completor.get() );

        // if it would not been completed externally, this program will never end
        System.out.println("tobe completed value " +  completableFutureToBeCompleted2.get() );
    }
}

