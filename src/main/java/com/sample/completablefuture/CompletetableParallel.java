package com.sample.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletetableParallel {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    demo1();
    demo2();
  }

  private static void demo1() throws InterruptedException, java.util.concurrent.ExecutionException {
    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1,future2);
    combinedFuture.get();
    System.out.println(future1.isDone());
    System.out.println(future2.isDone());
  }

  private static void demo2() throws InterruptedException, java.util.concurrent.ExecutionException {
    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

    String combined = Stream.of(future1,future2).map(CompletableFuture::join).collect(Collectors.joining(" "));
    System.out.println(combined);
  }
}
