package com.stiwa.gnomesort;

public class GnomeSort {
	static int[] array = new int[5000];

	public static void main(String[] args) {
		genNumbers();
		printNumbers();
	}

	private static void printNumbers() {
		for (int num : array) {
			System.out.println(num);
		}
	}

	public static void genNumbers() {
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 10000);
		}
	}
}
