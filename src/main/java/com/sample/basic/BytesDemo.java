package com.sample.basic;

import java.io.UnsupportedEncodingException;

/**
 * @author John_Yang
 */
public class BytesDemo {

  public static void main(String[] args) {
    String content = "Hello World";
    try {
      byte[] bytes = content.getBytes("UTF-8");
      for(byte tmp : bytes){
        System.out.println(tmp);
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
