package com.sample.collection;

import java.util.LinkedHashMap;

public class LinkedHashMapDemo {

  public static void main(String[] args) {
    LinkedHashMap<String,String> lhm = new LinkedHashMap<>();
    lhm.put("one", "practice.geeksforgeeks.org");
    lhm.put("two", "code.geeksforgeeks.org");
    lhm.put("four", "quiz.geeksforgeeks.org");
    System.out.println(lhm);
  }
}
