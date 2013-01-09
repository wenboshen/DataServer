package com.livetracker;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = "2013 || 2 || 7 || Feb";
		System.out.println(line);
		String[] values = line.split("\\|\\|");
		
		for (String value: values)
			System.out.println(value);
	}

}
