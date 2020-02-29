package com.sample.collection;

public class StringRepetionReduceDemo {
    public static void main(String[] args) {
        String input = "aaabbccbd";
        System.out.println(reduceRepetion(input));
    }

    public static String reduceRepetion(String input){
        StringBuffer result = new StringBuffer();
        char c,last = 0;
        for( int i=0;i<input.length();i++ ){
            c = input.charAt(i);
            if ( c != last ){
                result.append(c);
                last = c;
            }
        }

        return result.toString();
    }
}
