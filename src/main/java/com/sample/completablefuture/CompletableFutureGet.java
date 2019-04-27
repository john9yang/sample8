package com.sample.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureGet {

    public static void main(String[] args) {
        try{
            CompletableFuture<String> cf = new CompletableFuture<>();

            // it is possible to get the number of dependents on the completable Future
            System.out.println("number of dependents " +cf.getNumberOfDependents());

            System.out.println("get now "+cf.getNow("now"));

            //System.out.println("get in 3 seconds"+cf.get(5, TimeUnit.SECONDS));

            // get will wait for ever in this case
            System.out.println("get "+cf.get());
        }
//        catch( ExecutionException | InterruptedException | TimeoutException ex){
        catch( ExecutionException | InterruptedException ex){
            ex.printStackTrace();
        }
    }
}