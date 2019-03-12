package com.sample.thread.designPartern.singleton;

public class SingletonByEnum {
    //实例变量
    private byte[] data = new byte[1024];

    private SingletonByEnum(){

    }

    //使用枚举充当holder
    private enum EnumHolder{
        INSTANCE;

        private SingletonByEnum instance;

        EnumHolder(){
            System.out.println("EnumHolder will be initialized immediately");
            this.instance = new SingletonByEnum();
        }

        private SingletonByEnum getSingleton(){
            return instance;
        }
    }

    public static SingletonByEnum getInstance(){
        System.out.println("Enter getInstance");
        return EnumHolder.INSTANCE.getSingleton();
    }

}
