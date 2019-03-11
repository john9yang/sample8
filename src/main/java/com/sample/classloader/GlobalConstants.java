package com.sample.classloader;

import java.sql.SQLOutput;
import java.util.Random;

public class GlobalConstants {

    static {
        System.out.println("The GlobalConstants will be initialized");
    }

    public final static int MAX = 100;

    public final static int RANDOM = new Random().nextInt();

    public static void main(String[] args) {
        //System.out.println(MAX);
        System.out.println(RANDOM);
    }
}
