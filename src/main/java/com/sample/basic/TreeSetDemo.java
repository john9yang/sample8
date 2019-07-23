package com.sample.basic;

import model.Rule;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {
       Rule rule1 = new Rule("test1",1);
       Rule rule2 = new Rule("test2",2);

       Set<Rule> ruleSet = new TreeSet<> ();
       ruleSet.add(rule1);
       ruleSet.add(rule2);

       for( Rule rule : ruleSet ){
           System.out.println(rule);
       }
    }
}