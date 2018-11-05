package com.sample.queue;

import java.util.concurrent.DelayQueue;

public class DelayQueueTest {

	public static void main(String[] args) {
       
	   final DelayQueue<Student> bq = new DelayQueue<Student>();
       
	   for( int i=0;i<5;i++ ) {
    	   Student student = new Student("学生"+i,Math.round((Math.random()*10+i)));
    	   System.out.println(student.getName());
    	   bq.put(student);
       }
	   System.out.println("bq.peek()"+bq.peek().getName());
	}

}
