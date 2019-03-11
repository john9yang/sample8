package com.sample.classloader;

public class NameSpace{

    public static void main(String[] args) throws ClassNotFoundException{
        ClassLoader classLoader = NameSpace.class.getClassLoader();
        Class<?> aclass = classLoader.loadClass("com.sample.classloader.HelloWorld");
        Class<?> bclass = classLoader.loadClass("com.sample.classloader.HelloWorld");
        System.out.println(aclass.hashCode());
        System.out.println(bclass.hashCode());
        System.out.println(aclass == bclass );
    }
}