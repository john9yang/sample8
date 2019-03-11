package com.sample.classloader;

public class Parent {

    static {
        System.out.println("The parent is initialized");
    }

    public static int y = 100;
}
