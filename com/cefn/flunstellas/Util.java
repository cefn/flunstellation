package com.cefn.flunstellas;

import java.util.Random;

public class Util {
	
	private static Random random = new Random();

	public static long createRandomId(){
		return random.nextLong();
	}
}
