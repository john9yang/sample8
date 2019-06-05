package com.sample.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> mylist = new ArrayList<>();
        mylist.add(7);
        mylist.add(18);
        mylist.add(10);
        mylist.add(24);
        mylist.add(17);
        mylist.add(5);

        System.out.println("Original list:"+mylist);

        Stream<Integer> myStream = mylist.stream();
        Optional<Integer> minVal = myStream.min(Integer::compare);
        if ( minVal.isPresent() )
            System.out.println("Minimum value:"+minVal.get());

        myStream = mylist.stream();
        Optional<Integer> maxVal = myStream.max(Integer::compare);
        if ( maxVal.isPresent() )
            System.out.println("Maximum value:"+maxVal.get());

        Stream<Integer> sortedStream = mylist.stream().sorted();
        System.out.print("Sorted stream:");
        sortedStream.forEach((n)-> System.out.print(n+" ") );
        System.out.println();

        //display only the odd values by use of filter
        Stream<Integer> oddValsStream = mylist.stream().sorted().filter((n)-> (n%2)==1).filter((n)-> n>5);
        System.out.print("odd values:");
        oddValsStream.forEach((n)-> System.out.print(n+" "));
        System.out.println();
    }
}