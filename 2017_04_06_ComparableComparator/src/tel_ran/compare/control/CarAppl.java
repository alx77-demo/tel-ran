package tel_ran.compare.control;

import java.security.SecureRandom;
import java.util.Arrays;

import tel_ran.array.tools.ArrayTools;
import tel_ran.array.tools.CarCompareVendor;
import tel_ran.array.tools.CarCompareYear;
import tel_ran.array.tools.IComparable;
import tel_ran.compare.data.Car;

public class CarAppl {

	private static final int N_CARS = 10;
/*	private static final int MODE_A = 0001;
	private static final int MODE_B = 0010;
	private static final int MODE_C = 0100;
*/	

	public static void main(String[] args) {
		Car[] cars = new Car[N_CARS];
		generateCars(cars);
/*		int real_mode = MODE_A | MODE_C;//0101
		int checkAMode = real_mode & MODE_A;
*/		ArrayTools.printArray(cars);
		//ArrayTools.bubbleSort(cars);
		System.out.println("=======================");
		Arrays.sort(cars,new CarCompareVendor());
		ArrayTools.printArray(cars);
		System.out.println("=======================");
		Arrays.sort(cars,new CarCompareYear());
		ArrayTools.printArray(cars);

	}

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	private static String generateRandomString(int minLength, int maxLength) {
		int len = rnd.nextInt(maxLength - minLength) + minLength;
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	private static void generateCars(IComparable[] cars) {
		String vendors[] = { "Toyota", "Lada", "ZAZ", "VW", "GMC" };
		int displacements[] = { 1200, 1600, 2000, 2200, 2500 };

		for (int i = 0; i < cars.length; i++) {
			cars[i] = new Car(vendors[rnd.nextInt(vendors.length - 1)],
					generateRandomString(2, 3) + "-" + rnd.nextInt(9999),
					displacements[rnd.nextInt(displacements.length - 1)], rnd.nextInt(2017 - 1910) + 1910);
		}

	}

}
