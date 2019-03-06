package com.sample.stream;

import com.sample.entity.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
}


