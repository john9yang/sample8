package com.sample.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetDemo {

    public static void main(String[] args) {
        Set<Character> set =new HashSet<>();
        set.add('A');
        set.add('B');
        System.out.println(set);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            char c = (char)iterator.next();
            System.out.println("char:"+c);
            if ( c == 'A'){
                iterator.remove();
            }
        }
        System.out.println(set);
    }
}
