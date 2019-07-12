package com.sample.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambDaApp {

    public static void main(String[] args) throws Exception{
        List<String> names = Arrays.asList(new String[]{"Bela","Jani","John"});

        names.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String name) {
                return name.toLowerCase().startsWith("j");
            }
        }).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

//        names.stream().filter(name -> name.toLowerCase().startsWith("j"))
//                      .sorted(((o1, o2) -> o1.compareTo(o2)))
//                      .forEach(t-> System.out.println(t));

//        Callable<Runnable> c = new Callable<Runnable>() {
//            @Override
//            public Runnable call() throws Exception {
//                return new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("doing something here");
//                    }
//                };
//            }
//        };
//
//        Callable<Runnable> c = ()->()-> System.out.println("doing something here");
//        c.call().run();

    }
}