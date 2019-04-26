package com.sample.defaults.diamondproblem;

public class ClassImplementingDefaultInterfaces implements InterfaceAWithDefault, InterfaceBWithDefault
{
    /*
     * if this method is not implemented following compilation error occurs: Duplicate default
     * methods named defaultMethod with the parameters () and () are inherited from the types
     * InterfaceBWithDefault and InterfaceAWithDefault
     */
    public void defaultMethod()
    {
        InterfaceAWithDefault.super.defaultMethod();
    }

    public void callDefault()
    {
        this.defaultMethod();
    }

    @Override
    public void toImplementBMethod()
    {
        System.out.println( "toImplementBMethod " );

    }

    @Override
    public void toImplementAMethod()
    {
        System.out.println( "toImplementAMethod" );

    }

}