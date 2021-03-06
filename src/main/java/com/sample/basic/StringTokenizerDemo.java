package com.sample.basic;

import java.text.MessageFormat;
import java.util.StringTokenizer;

public class StringTokenizerDemo {
    public static final String hello = "hello";

    public static void main(String[] args) {
        String content = "oracle,java,ibm,jdk,spring";
        StringTokenizer st = new StringTokenizer(content,",;");
        while( st.hasMoreTokens() ){
           String token =st.nextToken();
            System.out.println("token:"+token);
        }

        String world="world";
        System.out.println(hello.concat("_").concat(world));

        String message = "test";
        MessageFormat formatter = new MessageFormat(message);
        System.out.println(formatter.format(new Object[]{}));
    }
}