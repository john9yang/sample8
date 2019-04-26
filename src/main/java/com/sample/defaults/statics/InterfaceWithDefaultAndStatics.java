package com.sample.defaults.statics;

public interface InterfaceWithDefaultAndStatics {

    public default void defaultMethod()
    {
        // it is possible to use interface static methods
        System.out.println( "I am the default method of the interface, give me five! " + giveMeFive() );
    }

    public static String giveMeFive()
    {
        return "5";
    }

    public void toImplementMethod();
}