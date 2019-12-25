package com.sample.classloader;

public class ExtClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        Class<?> simpleClass = Class.forName("com.sample.classloader.Simple");
        System.out.println(simpleClass.getClassLoader());
        System.out.println(ExtClassLoader.class.getClassLoader());
    }
}
