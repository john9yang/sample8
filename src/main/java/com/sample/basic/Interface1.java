package com.sample.basic;

public interface Interface1 {
    default void hello(){
        System.out.println("hello from interface1");
    }
}
