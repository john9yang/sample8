package com.sample.basic;

public interface Interface2 {
    default void hello(){
        System.out.println("hello from interface2");
    }
}
