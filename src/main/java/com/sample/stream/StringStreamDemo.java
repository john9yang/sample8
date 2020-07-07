package com.sample.stream;

import java.util.Arrays;
import java.util.List;

public class StringStreamDemo {

    public static void main(String[] args) {
        List<String> myList = Arrays.asList("area","block","building","city","country");

        myList.stream().filter(s -> s.startsWith("c"))
                       .map(String::toUpperCase)
                       .sorted()
                       .forEach(System.out::println);

        int sumOflenth = myList.stream().map(x->x.length()).mapToInt(x->x.intValue()).sum();
        System.out.println(sumOflenth);
    }
}
