package com.sample.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListDemo {

  public static void main(String[] args) {
    CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList();
    list.add(1);
    System.out.println(list);
  }

}
