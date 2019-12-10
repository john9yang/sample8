package com.sample.basic;

public class ExceptionDemo {

    public static void main(String[] args) {
        ExceptionDemo exceptionDemo = new ExceptionDemo();
        try{
            exceptionDemo.testMethod();
        }
        catch (ArithmeticException e){
            System.out.println("2");
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("1");
            e.printStackTrace();
        }
    }

    public void testMethod() throws Exception{
        //System.out.println(5/0);
//        throw new IllegalArgumentException("test");
          throw new ArithmeticException("test2");
    }
}
