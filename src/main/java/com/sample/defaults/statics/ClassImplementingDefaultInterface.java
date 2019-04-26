package com.sample.defaults.statics;

public class ClassImplementingDefaultInterface implements InterfaceWithDefaultAndStatics
{

    public void callDefault()
    {
        this.defaultMethod();
    }

    @Override
    public void toImplementMethod()
    {
        System.out.println( "to implement method" );

    }

}
