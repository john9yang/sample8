package com.sample.basic;

import java.util.StringTokenizer;

public class StringTokenizerDemo {

    public static void main(String[] args) {
        String content = "oracle,java,ibm,jdk,spring";
        StringTokenizer st = new StringTokenizer(content,",;");
        while( st.hasMoreTokens() ){
           String token =st.nextToken();
            System.out.println("token:"+token);
        }
    }
}