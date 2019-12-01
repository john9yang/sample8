package com.sample.basic;

public class MathDemo {
    public static void main(String[] args) {
        int number = 10;
        int len = (int)(Math.log(number)/Math.log(2)) ;
        System.out.println("len:"+len);
        number-=1<<len;
        System.out.println(number);
    }
}
