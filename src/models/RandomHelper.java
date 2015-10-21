package models;

import java.util.Random;


public final class RandomHelper {
	
	// This static class is an example of static helper to generate random numbers in a given range
	// in this case the class is defined final (cannot be extended by other classes)
	// and the constructor is private, to avoid to create an instance of it
	
	private RandomHelper(){}
	
	
	// generate random integer numbers in the range (min,max];
	public static int getRandomNumber(int min, int max){
		Random rand = new Random();
		int result;
		result = rand.nextInt((max - min) + 1) + min;
		return result;
	}
	
	// generate random string "date" in the format "dd-MM-yyyy";
	public static String getRandomDate(){
		int day = getRandomNumber(1,28);
		int month = getRandomNumber(1, 12);
		int year = getRandomNumber(1950, 2015);
		String S = day+"-"+month+"-"+year;
		return S;
	}
	
}
