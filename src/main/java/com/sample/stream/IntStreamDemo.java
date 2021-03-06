package com.sample.stream;

import java.util.stream.IntStream;

public class IntStreamDemo {
  public static void main(String[] args) {
    IntStream.range(0, 5)
        .boxed()
        .map(i -> new Thread(() -> System.out.println(Thread.currentThread().getName())))
        .forEach(Thread::start);

    System.out.println(IntStream.range(1, 5).reduce(1, (x, y) -> x * y));
  }
}