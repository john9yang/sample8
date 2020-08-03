package com.sample.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveDuplicatedWords {

    public static void main(String[] args) {
        String regex = "\\b(\\w+)(\\s+\\1\\b)+";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        String input = "The the string string string stringstring";
        Matcher m = pattern.matcher(input);
        while( m.find() ){
            input = input.replaceAll(m.group(),m.group(1));
            System.out.println("group:"+m.group());
            System.out.println("group1:"+m.group(1));
            System.out.println("Located ["+ m.group()+"] startint at "+ m.start()+" ending at "+(m.end()-1));
        }

        System.out.println(input);

    }


}
