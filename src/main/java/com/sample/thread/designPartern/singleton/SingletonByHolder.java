package com.sample.thread.designPartern.singleton;

//不允许被继承
public final class SingletonByHolder {

    private byte[] data = new byte[1024];

    private SingletonByHolder(){

    }

    //在静态内部类中持有singleton的实例,并且可被直接初始化
    private static class Holder{
        private static SingletonByHolder instance = new SingletonByHolder();
    }

    //获取Holder的instance静态属性
    public static SingletonByHolder getInstance(){
        return Holder.instance;
    }
}