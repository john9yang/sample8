package com.sample.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTimeoutExample {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> future = executorService.submit(FutureTimeoutExample::getFromServer);

    try {
      String result = future.get(10L, TimeUnit.SECONDS);
      System.out.println(result);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
    finally{
      executorService.shutdown();
    }
  }

  private static String getFromServer(){
    try {
      TimeUnit.SECONDS.sleep(15L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "200 ok";
  }
}
