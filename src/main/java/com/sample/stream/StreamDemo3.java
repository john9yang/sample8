package com.sample.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StreamDemo3 {
    public static void main(String[] args) {
        List<Double> mylist = new ArrayList<>();
        mylist.add(4.0);
        mylist.add(16.0);
//        mylist.add(10.0);
//        mylist.add(24.0);
//        mylist.add(17.0);
//        mylist.add(5.0);

        double productOfSqrRoots = mylist.parallelStream().reduce(1.0,
                                                                  (a,b) ->a*Math.sqrt(b),
                                                                  (a,b)->a*b);
        //1603.246705906486
//        double productOfSqrRoots = mylist.parallelStream().reduce(1.0,
//                (a,b) ->a*Math.sqrt(b));
        //25.34613228333002
//        double productOfSqrRoots = mylist.stream().reduce(1.0,(a,b) ->a*Math.sqrt(b));
        System.out.println("Product of square roots:"+productOfSqrRoots);
    }
}