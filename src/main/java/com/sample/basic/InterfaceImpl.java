package com.sample.basic;

public class InterfaceImpl implements Interface1,Interface2{

    @Override
    public void hello() {
        Interface1.super.hello();
    }

    public static void main(String[] args) {
        Interface1 interface1 = new InterfaceImpl();
        interface1.hello();
    }
}
