package com.sample.basic;

import javax.annotation.PostConstruct;

public class CharDemo {

	public static void main(String[] args) {

		char c1 = 'c';
		char c2 = 'b';
		
		System.out.println(c1 == c2);
		System.out.println(c1 != c2);
		
		System.out.println("cpu number:"+Runtime.getRuntime().availableProcessors());

		System.out.println(Character.isLetter('?'));
		CharDemo charDemo = new CharDemo();
		charDemo.displayInfo();
	}

	@PostConstruct
	public void init(){
    System.out.println("post construct entered");
	}

	public void displayInfo(){
		System.out.println("display cpu number:"+Runtime.getRuntime().availableProcessors());
	}

}
