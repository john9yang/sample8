package com.sample.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentHashMapDemo {

    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();

        new Thread(()->{
         map.put("java",90);
         map.put("datastrucure",95);
         map.put("datastrucure",95);
         map.put("network",85);
        },"t1").start();

        new Thread(()->{
            map.put("english",90);
        },"t2").start();

        new Thread(()->{
            Set<Map.Entry> entrySet =  map.entrySet();
            Iterator<Map.Entry> iterator = entrySet.iterator();
            while(iterator.hasNext()){
                Map.Entry entry = iterator.next();
                System.out.printf("key:"+entry.getKey()+"--value:"+entry.getValue());
            }
        },"t3").start();
    }
}