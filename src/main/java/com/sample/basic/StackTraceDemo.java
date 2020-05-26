package com.sample.basic;

public class StackTraceDemo {
    public static void main(String[] args) {
        doTrace();
    }

    public static void doTrace(){
        StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
        for (StackTraceElement element: stackTrace) {
            System.out.println(element.getMethodName());
        }
    }
}
