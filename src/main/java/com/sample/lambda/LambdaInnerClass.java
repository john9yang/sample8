package com.sample.lambda;

import java.util.function.Consumer;

public class LambdaInnerClass {

  public static Runnable helloWorld(){
    return () -> System.out.println("Hello World");
  }

  public static Consumer<String> printMe(){
    //may create a new object each time = Garbage
    return System.out::println;
  }

  public static Consumer<String> printMe2(){
    return x -> System.out.println(x);
  }

  public static void main(String[] args) {
    Runnable r1 = helloWorld();
    Runnable r2 = helloWorld();
    System.out.println(r1 == r2 );

    Consumer<String> c1 = printMe();
    Consumer<String> c2 = printMe();
    System.out.println(c1 == c2);

    Consumer<String> c3 = printMe2();
    Consumer<String> c4 = printMe2();
    System.out.println(c3 == c4);
  }
}
