package com.sample.basic;

public class IntBitDemo {

  public static void main(String[] args) {
      //0001 0100
      int i = 20;
      System.out.println("binary:"+Integer.toBinaryString(i));
      System.out.println("20 & 1 ="+(i&1));
  }
}
