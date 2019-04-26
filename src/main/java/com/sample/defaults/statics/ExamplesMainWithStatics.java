package com.sample.defaults.statics;

public class ExamplesMainWithStatics {

    public static void main(String[] args) {
        InterfaceWithDefaultAndStatics instanceWithDefault = new ClassImplementingDefaultInterface();

        instanceWithDefault.defaultMethod();

        // it is possible to call static methods directly to the interface
        InterfaceWithDefaultAndStatics.giveMeFive(); // right

        // wrong: The method giveMeFive() is undefined for the type ClassImplementingDefaultInterface
        //ClassImplementingDefaultInterface.giveMeFive();
    }
}