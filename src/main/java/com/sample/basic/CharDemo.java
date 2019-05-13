package com.sample.basic;

public class CharDemo {

	public static void main(String[] args) {

		char c1 = 'c';
		char c2 = 'b';
		
		System.out.println(c1 == c2);
		System.out.println(c1 != c2);
		
		System.out.println("cpu number:"+Runtime.getRuntime().availableProcessors());

		System.out.println(Character.isLetter('?'));
	}

}
