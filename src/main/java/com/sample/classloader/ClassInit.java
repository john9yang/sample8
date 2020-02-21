package com.sample.classloader;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ClassInit {

  static{
    try{
      System.out.println("The ClassInit static code block will be invoke.");
      TimeUnit.SECONDS.sleep(3l);
    }
    catch (InterruptedException e){
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    IntStream.range(0,5).forEach(i -> new Thread(ClassInit::new));
  }
}
