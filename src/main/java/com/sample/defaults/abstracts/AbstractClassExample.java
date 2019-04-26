package com.sample.defaults.abstracts;

public abstract class AbstractClassExample {
    int cachedTwo = -1;

    public abstract int calculateTwoPlusTwo();

    public int returnTwo()
    {
        if( cachedTwo != -1 )
            return cachedTwo;
        cachedTwo = 2;
        return 2;
    }
}
