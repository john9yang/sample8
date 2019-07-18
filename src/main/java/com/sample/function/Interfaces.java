package com.sample.function;

import java.util.Comparator;

public class Interfaces {

    public static void main(String[] args) {
         Employee mike = new Employee("Mike",2000);
         Employee louise = new Employee("Louise",2500);

        Comparator<Employee> byName = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        System.out.println("By name:");
        System.out.println(byName.compare(mike,louise));

        try {
            // throws NPE
            System.out.println(byName.compare(mike, null));
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        //a static method in comparator
        Comparator<Employee> byNameThenNull = Comparator.nullsLast(byName);
        System.out.println("Then null:");
        System.out.println(byNameThenNull.compare(mike,louise));
        System.out.println(byNameThenNull.compare(null,null));
        System.out.println(byNameThenNull.compare(mike,null));
        System.out.println(byNameThenNull.compare(null,mike));

        //a default method in Comparator
        Comparator<Employee> nullThenByDecreasingName = byNameThenNull.reversed();
        System.out.println("Reversed:");
        System.out.println(nullThenByDecreasingName.compare(mike,louise));
        System.out.println(nullThenByDecreasingName.compare(mike, null));
    }
}
