package com.sample.classloader;

public class Child extends Parent{

    static {
        System.out.println("The child will be initialized");
    }

    public static int x = 10;
}
