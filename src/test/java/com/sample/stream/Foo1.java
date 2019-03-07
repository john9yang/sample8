package com.sample.stream;

import com.sample.entity.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Foo1 {

    private Random random;
    private List<Student> stuList;

    @Before
    public void init(){
        random = new Random();
        stuList = new ArrayList<Student>();
        for( int i=0 ; i<100; i++ ){
            stuList.add(new Student("Student"+i,random.nextInt(50)+50));
        }
    }

    @Test
    public void  test1(){
        List<String> studentNameList = stuList.stream()
                     .filter(x -> x.getScore() > 85)
                     .sorted(Comparator.comparing(Student::getScore).reversed())
                     .map(Student::getName)
                     .collect(Collectors.toList());
        System.out.println(studentNameList);
    }

    @Test
    public void test2(){
        Stream<String> stream = Stream.generate(()->"user").limit(5);
        stream.forEach(System.out::println);
    }

    @Test
    public void test3(){
        Stream<Student> stream = stuList.stream().filter(this::filter);
        System.out.println("split------------");
        List<Student> studentList = stream.collect(Collectors.toList());
    }

    @Test
    public void test4(){
        List<String> wordList = new ArrayList<String>();
        wordList.add("a");
        wordList.add("b");
        wordList.add("c");

        Stream<String> words = wordList.stream();
        wordList.add("e");
        long n = words.distinct().count();
        System.out.println(n);
    }

    /**
     * 通过数组创建流
     * @param
     * @return
     */
    @Test
    public void testArrayStream(){
        //1.通过Arrays.stream
        //1.1 基本类型
        int[] arr = new int[]{1,2,34,5};
        IntStream intStream = Arrays.stream(arr);
        //1.2 引用类型
        Student[] studentArr = new Student[]{new Student("s1",29),new Student("s2",27)};
        Stream<Student> studentStream = Arrays.stream(studentArr);

        //2.通过Stream.of
        Stream<Integer> stream1 = Stream.of(1,2,34,5);
    }

    /**
     * 通过集合创建流
     * @param
     * @return
     */
    @Test
    public void testCollectionStream(){
        List<String>  strs = Arrays.asList("123","456","789");
        //创建普通流
        Stream<String> stream = strs.stream();
        //创建并行流
        Stream<String> stream1 = strs.parallelStream();
    }

    /**
     * 创建空的流
     * @param
     * @return
     */
    @Test
    public void testEmptyStream(){
        //创建一个空的stream
        Stream<Integer> stream = Stream.empty();
    }

    //创建无限流
    @Test
    public void testUnlimitStream(){
        Stream.generate(()->"number"+new Random().nextInt()).limit(100).forEach(System.out::println);
        Stream.generate(()->new Student("name",10)).limit(3).forEach(System.out::println);
    }

    //产生规律的数据
    @Test
    public void testUnlimitStream1(){
        //Stream.iterate(0,x->x+1).limit(10).forEach(System.out::println);
        //Stream.iterate(0,x->x).limit(10).forEach(System.out::println);
        Stream.iterate(0, UnaryOperator.identity()).limit(10).forEach(System.out::println);
    }

    /**
     * map把一种类型的流转换为另外一种类型的流
     */
    @Test
    public void testMap(){
        String[] arr = new String[]{"yes","YES","no","NO"};
        Arrays.stream(arr).map(x -> x.toLowerCase()).forEach(System.out::println);
    }

    @Test
    public void testFilter(){
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9};
        Arrays.stream(arr).filter(x -> x>3&&x<8).forEach(System.out::println);
    }

    /**
     * flapMap：拆解流
     */
    @Test
    public void testFlapMap1() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"e", "f", "c", "d"};
        String[] arr3 = {"h", "j", "c", "d"};
        Stream.of(arr1, arr2, arr3).flatMap(x -> Arrays.stream(x)).forEach(System.out::println);
        //Stream.of(arr1, arr2, arr3).flatMap(Arrays::stream).forEach(System.out::println);
    }

    public boolean filter(Student s){
        System.out.println("begin compare");
        return s.getScore() > 85;
    }

    /**
     * Comparator.comparing是一个键提取的功能
     * 以下两个语句表示相同意义
     */
    @Test
    public void testSorted1_(){
        /**
         * 按照字符长度排序
         */
        String[] arr1 = {"abc","a","bc","abcd"};
//        Arrays.stream(arr1).sorted((x,y)->{
//            if (x.length()>y.length())
//                return 1;
//            else if (x.length()<y.length())
//                return -1;
//            else
//                return 0;
//        }).forEach(System.out::println);
        Arrays.stream(arr1).sorted(Comparator.comparing(String::length)).forEach(System.out::println);
    }

    /**
     * 倒序
     * reversed(),java8泛型推导的问题，所以如果comparing里面是非方法引用的lambda表达式就没办法直接使用reversed()
     * Comparator.reverseOrder():也是用于翻转顺序，用于比较对象（Stream里面的类型必须是可比较的）
     * Comparator. naturalOrder()：返回一个自然排序比较器，用于比较对象（Stream里面的类型必须是可比较的）
     */
    @Test
    public void testSorted2_(){
        String[] arr1 = {"abc","a","bc","abcd"};
        Arrays.stream(arr1).sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
        System.out.println("------------------------------------------");
        Arrays.stream(arr1).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println("------------------------------------------");
        Arrays.stream(arr1).sorted(Comparator.naturalOrder()).forEach(System.out::println);
    }

    /**
     * thenComparing
     * 先按照首字母排序
     * 之后按照String的长度排序
     */
    @Test
    public void testSorted3_(){
        String[] arr1 = {"abc","a","bc","abcd"};
        Arrays.stream(arr1).sorted(Comparator.comparing(this::com1).thenComparing(String::length)).forEach(System.out::println);
    }
    public char com1(String x){
        return x.charAt(0);
    }
}


