package com.sample.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class JavaAsyncCompleteFuture {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    long serialBegin = System.currentTimeMillis();
    int number = 20;
    System.out.println("serial Factorial of "+number+" is "+factorial(number));
    long serialEnd = System.currentTimeMillis();
    System.out.println(" factorial serial takes:"+ (serialEnd-serialBegin)+" ms");

    long serialBegin2 = System.currentTimeMillis();
    Future<Long> completableFuture = factorialUsingCompletableFuture(number);
    System.out.println("Factorial of " + number + " is: " + completableFuture.get());
    long serialEnd2 = System.currentTimeMillis();
    System.out.println(" factorial complete takes:"+ (serialEnd2-serialBegin2)+" ms");
  }

  /**
   * Finds factorial of a number
   * @param number
   * @return
   */
  public static long factorial(int number) {
    long result = 1;
    for(int i=number;i>0;i--) {
      result *= i;
    }
    return result;
  }

  public static Future<Long> factorialUsingCompletableFuture(int number) {
    CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> factorial(number));
    return completableFuture;
  }


}