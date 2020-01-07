package com.test.simplecalculator;

public class Arith{
	static int add(String a, String b){
		return Integer.parseInt(a)+Integer.parseInt(b);
	}

	static int sub(String a, String b){
		return Integer.parseInt(a)-Integer.parseInt(b);
	}

	static int mul(String a, String b){
		return Integer.parseInt(a)*Integer.parseInt(b);
	}

	static int div(String a, String b){
		return Integer.parseInt(a)/Integer.parseInt(b);
	}
}
