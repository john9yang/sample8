package com.sample.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BrokerDelegateClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException,
            NoSuchMethodException, InvocationTargetException {
        {
            BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader();
            Class<?> aClass = classLoader.loadClass("com.sample.classloader.HelloWorld");
            System.out.println("classloader:" + aClass.getClassLoader());

            Object helloword = aClass.newInstance();
            System.out.println(helloword);

            Method welcomeMethod = aClass.getMethod("welcome");
            String result = (String) welcomeMethod.invoke(helloword);
            System.out.println("Result:" + result);
        }
    }
}