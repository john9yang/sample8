package com.sample.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleDemo {
    public static void main(String[] args) {
        String key="crn/*/*/*";
        String keyformated = key.replaceAll("\\*", "[^/?#]*");
        System.out.println(keyformated);
        Pattern p = Pattern.compile("^"+keyformated+"");

        Matcher m = p.matcher("crn/*///#******/#*");
        boolean ismatch = m.lookingAt();
        System.out.println(ismatch);
    }
}
