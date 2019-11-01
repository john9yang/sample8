package com.sample.lambda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Developer;

public class SortTest {

  public static void main(String[] args) {
    Comparator<Developer> byName = new Comparator<Developer>() {
      @Override
      public int compare(Developer o1, Developer o2) {
        return o1.getName().compareTo(o2.getName());
      }
    };

    Comparator<Developer> byNameLambda = (Developer o1,Developer o2) -> o1.getName().compareTo(o2.getName());

    List<Developer> listDevs = getDevelopers();

    System.out.println("Before Sort");
    for (Developer developer : listDevs) {
      System.out.println(developer);
    }

    //sort by age
    Collections.sort(listDevs, new Comparator<Developer>() {
      @Override
      public int compare(Developer o1, Developer o2) {
        return o1.getAge() - o2.getAge();
      }
    });

    listDevs.sort((Developer o1,Developer o2) -> o1.getAge()-o2.getAge());

    System.out.println("After Sort");
    //    for (Developer developer : listDevs) {
    //      System.out.println(developer);
    //    }
    listDevs.forEach((developer -> System.out.println(developer)));
  }

  private static List<Developer> getDevelopers() {

    List<Developer> result = new ArrayList<Developer>();

    result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
    result.add(new Developer("alvin", new BigDecimal("80000"), 20));
    result.add(new Developer("jason", new BigDecimal("100000"), 10));
    result.add(new Developer("iris", new BigDecimal("170000"), 55));

    return result;

  }
}
