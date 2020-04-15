package com.sample.lambda;

public class SerializableConsumerDemo {

  public SerializableConsumer<String> printMe(){
    return System.out::println;
  }

  public SerializableConsumer<String> printMe2(){
    return x -> System.out.println(x);
  }

  public SerializableConsumer<String> printMe3(){
    return new SerializableConsumer<String>() {
      @Override
      public void accept(String s) {
        System.out.println(s);
      }
    };
  }

  public static void main(String[] args) {
    SerializableConsumerDemo demo = new SerializableConsumerDemo();
    demo.printMe();
    demo.printMe2();
    demo.printMe3();
  }
}
