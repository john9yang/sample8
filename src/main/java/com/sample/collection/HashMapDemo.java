package com.sample.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author John_Yang
 */
public class HashMapDemo {

    public static void main(String[] args) {
        Map map = new HashMap(4);
        map.put("java",80);
        map.put("java",90);

        Set<Map.Entry> entrySet = map.entrySet();
        Iterator<Map.Entry> iterator =  entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry entry = iterator.next();
            System.out.println("key:"+entry.getKey()+"-"+"value:"+entry.getValue());
        }


        Map<String,Object> map2 = new HashMap(4){
            {
                put("java",100);
                put("name","john");
                put("age",30);
            }
        };

        map2.entrySet().stream().forEach(e -> System.out.println("stream key:"+e.getKey()));
    }
}