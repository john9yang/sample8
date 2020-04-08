package com.sample.basic;

import java.util.Random;

public class StaticUitls {

  public static int time;

  static{
    time = new Random().nextInt(100);
    System.out.println("times:"+time);
  }

  public static void save(){
    System.out.println("call save");
  }
}
