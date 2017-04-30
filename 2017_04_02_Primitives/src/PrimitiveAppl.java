
public class PrimitiveAppl {

	public static void main(String[] args) {
		greetings();
		System.out.println("Hello world!");
		byte a = 127;
		a += 2;
		Integer i = (int) a;
		String s = String.valueOf(i);
		System.out.println(s);
		// --a;
		// a = (byte) (a + 10.5);
		float b = a;
		float f = 7.5f % (float) 2.4;
		System.out.println("f:" + f);
		double d = 7.5 % 2.4;
		System.out.println("d:" + d);
		double e = 7. / 2;
		System.out.println("e:" + e);
		b = ++a + ++b;
		System.out.println("b:" + b);
		boolean z = 2. == 2;
		System.out.println(z);
		double k = 0, l = 4;
		System.out.println(getSolution(k, l));
		int age = 20;
		int vol = age >= 18 ? 42 : 18;
		System.out.println(vol);
		System.out.println(abs(5.3));
		fan(333);
		System.out.println(digitSum(181));
		System.out.println(countDigits(131));
		System.out.println();
		System.out.println(isHappyTicket(753)?"happy":"unhappy");
	}

	// public static <T extends Comparable<T>> T max(T left, T right) {
	// return left>right?left:right;
	// }

	public static double max(double left, double right) {
		return left > right ? left : right;
	}

	public static double min(double left, double right) {
		return -max(-left, -right);
	}

	public static double abs(double val) {
		double res;
		if (val % 1 > 0) {
			res = val - val % 1;
		} else {
			res = val;
		}
		return res;
	}

	public static void fan(int mode) {
		switch (mode) {
		case 0:
			System.out.println("Off");
			break;
		case 1:
			System.out.println("On");
			break;
		case 2:
		case 3:
			System.out.println("Power mode");
			break;
		default:
			System.out.println("boooo");

		}
	}

	public static int digitSum(int in) {
		int val = in, res = 0;

		while (0 != val) {
			res += val % 10;
			val /= 10;
		}
		return res;
	}

	public static int countDigits(int in) {
		int val = in, res = 0;

		do {
			res++;
			val /= 10;
		} while (0 != val);

		return res;
	}

	public static boolean isHappyTicket(int in) {
		int val = in, res = 0;
		//int length = (int) Math.log10(in);
		//System.out.println(length);
		int oddSum = 0, evenSum = 0, digit;

		for (int i = 0; val != 0; ++i) {
			if (i % 2 == 1)
				evenSum += val % 10;
			else
				oddSum += val % 10;
			val /= 10;
		}
		return evenSum == oddSum;
	}

	public static boolean luckyNumber(int num) {
		int sum = 0;
		int i = 1;
		while (num != 0) {
			sum = sum + i * num % 10;
			num = num / 10;
			i = -i;
		}
		return sum == 0;
	}
	
	public static boolean isHappyTicket2(int in) {
		int length = (int) Math.log10(in);
		System.out.println(length);
		int rightSum = 0, leftSum = 0, digit;

		for (int i = 0; i<length; ++i) {
			if (i < length/2)
				rightSum += in % 10;
			else
				leftSum += in % 10;
			in /= 10;
		}
		return leftSum  == rightSum;
	}

	public static void greetings() {
		System.out.println("Congrats!");
		printName();
	}

	public static void printName() {
		System.out.println("Peter");
	}

	public static double getSolution(double a, double b) {
		double res = -b / a;
		return res;
	}
}
