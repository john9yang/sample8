package com.sample.defaults.abstracts;

public interface InterfaceDefaultExample
{

    int cachedTwo = -1;

    public int calculateTwoPlusTwo();

    public default int returnTwo()
    {
        if( cachedTwo != -1 ) //warning: Comparing identical expressions
            return cachedTwo; //warning: Dead code
        //cachedTwo = 2;
        return 2;
    }

}