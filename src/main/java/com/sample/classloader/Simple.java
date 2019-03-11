package com.sample.classloader;

public class Simple {

    static
    {
        System.out.println("I will be initialized");
    }

    public static int x  = 10;

    public static void test(){

    }

    public static void main(String[] args) {
        //int x = Simple.x;

        // Simple.test();

        try {
            Class.forName("com.sample.classloader.Simple");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
