package com.sample.defaults.diamondproblem;

public interface InterfaceBWithDefault
{

    public default void defaultMethod()
    {
        System.out.println( "I am the default method of the InterfaceBWithDefault " );
    }

    public void toImplementBMethod();
}