package com.sample.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentHashMapDemo {

    public static void main(String[] args) {

        CurrentHashMapDemo currentHashMapDemo = new CurrentHashMapDemo();
        currentHashMapDemo.op();
    }

    public void op(){

        Map map = new ConcurrentHashMap();

        new Thread(()->{
            while(true){
                map.put("java",90);
                map.put("datastrucure",95);
                map.put("datastrucure",95);
                map.put("network",85);
            }
        },"t1").start();

        new Thread(()->{
            while( true ){
                map.put("english",90);
                map.put("java",180);
                map.put("network",170);
            }
        },"t2").start();

        new Thread(()->{
            while (true){
                Set<Map.Entry> entrySet =  map.entrySet();
                Iterator<Map.Entry> iterator = entrySet.iterator();
                while(iterator.hasNext()){
                    Map.Entry entry = iterator.next();
                    System.out.println("key:"+entry.getKey()+"--value:"+entry.getValue());
                }
            }
        },"t3").start();
    }
}