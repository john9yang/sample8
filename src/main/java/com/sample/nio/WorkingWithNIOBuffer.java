package com.sample.nio;

import java.nio.CharBuffer;

public class WorkingWithNIOBuffer {

  public static void main(String[] args) {
    char[] myArray = new char[100];
    CharBuffer charBuffer = CharBuffer.wrap(myArray,0,42);
    charBuffer.put('h').put('e').put('l').put('l').put('0');
    System.out.println("cap:"+charBuffer.capacity());
    System.out.println("position:"+charBuffer.position());
    System.out.println("limit:"+charBuffer.limit());
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
    //flip
    charBuffer.flip();
    System.out.println("cap:"+charBuffer.capacity());
    System.out.println("position:"+charBuffer.position());
    System.out.println("limit:"+charBuffer.limit());
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
    //compact
    charBuffer.compact();
    System.out.println("cap:"+charBuffer.capacity());
    System.out.println("position:"+charBuffer.position());
    System.out.println("limit:"+charBuffer.limit());
  }
}
