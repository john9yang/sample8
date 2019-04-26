package com.sample.defaults.diamondproblem;

public interface InterfaceAWithDefault {

    public default void defaultMethod()
    {
        System.out.println( "I am the default method of the InterfaceAWithDefault " );
    }

    public void toImplementAMethod();
}
