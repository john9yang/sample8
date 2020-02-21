package guava.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;
import java.util.stream.IntStream;

public class ListPartitionDemo {
    public static void main(String[] args) {
        List<Integer> results = new ArrayList<>();
        IntStream.range(0,99).forEach(i ->results.add(i));

        System.out.println(results instanceof RandomAccess);

        List<List<Integer>> partitions = Lists.partition(results, 10);
        for (List<Integer> list : partitions) {
            System.out.println(list);
        }
    }
}
