package com.sample.collection;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

public class WeakKeyCurrentHashMapDemo {

  public static void main(String[] args) {
    ConcurrentHashMap<WeakReference<Thread>,Object> map = new ConcurrentHashMap();
    WeakReference<Thread> currentThread = new WeakReference<>(Thread.currentThread());
    map.put(currentThread,new String("test"));
    System.out.println(map.get(currentThread));
  }
}