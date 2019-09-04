package com.sample.executor;

import com.sample.executor.TimeoutController.TimeoutException;
import java.util.concurrent.TimeUnit;

public final class TimeoutControllerTest {

  public static void main(String[] args) {
    try {
      TimeoutController.execute(
          () -> {
            try {
              TimeUnit.SECONDS.sleep(6L);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println("test");
          },
          5000L);
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }
}
