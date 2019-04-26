package com.sample.defaults;

public class ClassImplementingDefaultInterface implements  InterfaceWithDefault{
    @Override
    public void toImplementMethod() {
        System.out.println( "to implement method" );
    }

    public void callDefault()
    {
        this.defaultMethod();
    }
}
