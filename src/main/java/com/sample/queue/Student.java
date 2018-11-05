package com.sample.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Student implements Delayed{
	private String name;
	private long submitTime;  //交卷时间
	private long workTime;    //考试时间

	public String getName() {
		return this.name+" 交卷,用时"+workTime;
	}
	
	public Student(String name,long submitTime) {
		this.name = name;
		this.workTime = submitTime;
		this.submitTime = TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS)+System.nanoTime();
	}
	
	@Override
	public int compareTo(Delayed o) {
		Student that = (Student) o;
		
		return submitTime > that.submitTime?1:(submitTime < that.submitTime ? -1:0);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		//返回一个延时时间
		return unit.convert(submitTime-System.nanoTime(), unit.NANOSECONDS);
	}

}