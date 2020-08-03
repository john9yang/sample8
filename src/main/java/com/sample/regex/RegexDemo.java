package com.sample.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        String regex = "\\b(\\w+)(\\s+\\1\\b)+";
        String input = "Java Java";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()){
            System.out.println("Located ["+ matcher.group()+"] startint at "+ matcher.start()+" ending at "+(matcher.end()-1));
            System.out.println("group1:"+matcher.group(1));
        }
    }
}
