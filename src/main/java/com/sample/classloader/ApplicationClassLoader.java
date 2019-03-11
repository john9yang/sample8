package com.sample.classloader;

import jdk.nashorn.internal.runtime.regexp.joni.ApplyCaseFoldArg;

public class ApplicationClassLoader {

    public static void main(String[] args) {
        System.out.println("path:"+System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}