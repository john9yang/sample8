package com.sample.stream;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.Book;

public class List2MapDemo {
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));

        Map<Integer,Book> mapWithDupKey = bookList.stream().collect(
                Collectors.toMap(Book::getReleaseYear, Function.identity(),(existing,replacement)->existing));
        mapWithDupKey.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });

        Map<Integer,Book> conMapWithDupKey = bookList.stream().collect(Collectors.toMap(Book::getReleaseYear,Function.identity(),
                (o1,o2)->o1, ConcurrentHashMap::new));
        conMapWithDupKey.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });
    }
}