package com.sample.stream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo2 {
    public static void main(String[] args) {
        List<Integer> mylist = new ArrayList<>();
        mylist.add(7);
        mylist.add(18);
        mylist.add(10);
        mylist.add(24);
        mylist.add(17);
        mylist.add(5);

        Integer result = mylist.stream().filter( i -> i%81 == 0).findAny().orElse(0);
        System.out.println("result:"+result);

//        Optional<Integer> productObj = mylist.stream().reduce((a,b) -> a*b);
        Optional<Integer> productObj = mylist.parallelStream().reduce((a,b) -> a*b);
        if ( productObj.isPresent() )
            System.out.println("Product as Optional:"+productObj.get());

//        int product = mylist.stream().reduce(1,(a,b) -> a*b);
        int product = mylist.parallelStream().reduce(1,(a,b) -> a*b,(a,b) -> a*b);
        System.out.println("Product as int:"+product);

        int evenProduct = mylist.stream().reduce(1,(a,b)->{
           if ( b % 2 == 0 )
               return a*b;
           else
               return a;
        });
        System.out.println("evenProduct:"+evenProduct);
    }
}