package com.sample.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureFactories {

    public static void main(String[] args) throws Exception {
        // we can create a completable in asynchronus mode and pass it directly a task to do
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "100";
        });

        // it is possible to "join" completables, even if their type is not the same
        CompletableFuture<Integer> theApplyThis = completableFuture.thenApply(Integer::parseInt);

        CompletableFuture<Double> andThenThis = theApplyThis.thenApply( r-> r*10.0 );

        System.out.println("first:"+completableFuture.get());
        System.out.println("second:"+theApplyThis.get());
        System.out.println("third:"+andThenThis.get());

        completableFuture.thenAcceptAsync(x-> System.out.println(x.length()));
        System.out.println("lambdas:"+completableFuture.get());

        CompletableFuture<Integer> completableFuture1Exception = CompletableFuture.supplyAsync(()->{
//           return 10/2;
           return 10/0;
        });
        CompletableFuture<Integer> fallback = completableFuture1Exception.exceptionally(x->{
            System.out.println("test");
            return 0;
        });
        System.out.println("Result:"+fallback.get());

        CompletableFuture<Integer> completableFutureHandleOk = CompletableFuture.supplyAsync( ( ) -> {
            // big task
            return 10 / 0; // exception division by zero
             //return 10 / 2;
        } );

        // or handling exceptions in case they arise
        CompletableFuture<Integer> handleOkError = completableFutureHandleOk.handle( ( ok, ex ) -> {
            if( ok != null )
            {
                // return the value if everything ok
                return ok;
            }
            else
            {
                // in case of an exception print the stack trace and return null
                ex.printStackTrace();
                return null;
            }
        } );

        System.out.println( "ok or error ? " + handleOkError.get() );
    }
}
