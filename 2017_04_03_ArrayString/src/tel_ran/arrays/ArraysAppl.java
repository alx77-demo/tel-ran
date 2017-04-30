package tel_ran.arrays;

import java.util.Arrays;

import tel_ran.arrays.tools.ArraysTools;

public class ArraysAppl {

	public static void main(String[] args) {
		int[] arr = { 8, 9, 10 }, arr1;
		arr1 = new int[10];
		ArraysTools.fillArrayRandom(arr1, 10, 100);
		System.out.println(arr.length);
		//ArraysTools.invertSign(arr);
		ArraysTools.printArray(arr);
		int val = 9;
		changeSign(val);// sent copy of the primitive type
		System.out.println(val);
		System.out.println();
		ArraysTools.printArray(arr1);
		System.out.println();
//		ArraysTools.bubbleSort(arr1);
		Arrays.sort(arr1);
		ArraysTools.printArray(arr1);
		System.out.println(ArraysTools.binarySearch(arr1, 65));
	}

	private static void changeSign(int val) {
		val = -val;
	}

}
