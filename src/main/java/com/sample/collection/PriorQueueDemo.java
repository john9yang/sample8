package com.sample.collection;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((v1,v2)->{return v1-v2;});
        priorityQueue.add(1);
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(2);

        System.out.println(priorityQueue);
    }
}
