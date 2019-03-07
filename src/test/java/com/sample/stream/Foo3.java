package com.sample.stream;

import com.sample.entity.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Foo3 {
    private String[] arr;

    @Before
    public void init(){
        arr = new String[]{"b","ab","abc","abcd","abcde"};
    }

    /**
     * max、min
     * 最大最小值
     */
    @Test
    public void testMaxAndMin(){
        Stream.of(arr).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
        Stream.of(arr).min(Comparator.comparing(String::length)).ifPresent(System.out::println);
    }

    /**
     * count
     * 计算数量
     */
    @Test
    public void testCount(){
        long count = Stream.of(arr).count();
        System.out.println(count);
    }

    /**
     * findFirst
     * 查找第一个
     */
    @Test
    public void testFindFirst(){
        String str =  Stream.of(arr).parallel().filter(x->x.length()>3).findFirst().orElse("noghing");
        System.out.println(str);
    }

    /**
     * findAny
     * 找到所有匹配的元素
     * 对并行流十分有效
     * 只要在任何片段发现了第一个匹配元素就会结束整个运算
     */
    @Test
    public void testFindAny(){
        Optional<String> optional = Stream.of(arr).parallel().filter(x->x.length()>3).findAny();
        optional.ifPresent(System.out::println);
    }

    /**
     * anyMatch
     * 是否含有匹配元素
     */
    @Test
    public void testAnyMatch(){
        Boolean aBoolean = Stream.of(arr).anyMatch(x->x.startsWith("a"));
        System.out.println(aBoolean);
    }


    @Test
    public void testStream1() {
        Optional<Integer> optional = Stream.of(1,2,3,4,5,6).filter(x->x>1).reduce((x, y)->x+y);
        System.out.println(optional.get());
    }

    @Test
    public void testOptional() {
        List<String> list = new ArrayList<String>() {
            {
                add("user1");
                add("user2");
            }
        };
        Optional<String> opt = Optional.of("andy with u");
        opt.ifPresent(list::add);
        list.forEach(System.out::println);
    }

    @Test
    public void testOptional2() {
        Integer[] arr = new Integer[]{4,5,6,7,8,9};
        Integer result = Stream.of(arr).filter(x->x>9).max(Comparator.naturalOrder()).orElse(-1);
        System.out.println(result);
        Integer result1 = Stream.of(arr).filter(x->x>9).max(Comparator.naturalOrder()).orElseGet(()->-1);
        System.out.println(result1);
        Integer result2 = Stream.of(arr).filter(x->x>9).max(Comparator.naturalOrder()).orElseThrow(RuntimeException::new);
        System.out.println(result2);
    }

    @Test
    public void testStream3() {
        Optional<Student> studentOptional = Optional.of(new Student("user1",21));
        Optional<String> optionalStr = studentOptional.map(Student::getName);
        System.out.println(optionalStr.get());
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

    /**
     * Optional的迭代
     */
    @Test
    public void testStream2() {
        double x = 4d;
        Optional<Double> result1 = inverse(x).flatMap(Foo3::squareRoot);
        result1.ifPresent(System.out::println);
        Optional<Double> result2 = Optional.of(4.0).flatMap(Foo3::inverse).flatMap(Foo3::squareRoot);
        result2.ifPresent(System.out::println);
    }



}
