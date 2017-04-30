
public class PrimitivesRange {

	public static void main(String[] args) {
		System.out.println(getMaxInt());
		System.out.println(Integer.MAX_VALUE);
		System.out.println(getMaxLong());
		System.out.println(Long.MAX_VALUE);
	}

	private static int getMaxInt() {
		// int i = 1;
		// while (i>0) i++;
		// return i-1;
		int i = 1;
		while (i > 0)
			i *= 2;

		return i - 1;
	}

	private static long getMaxLong() {
		long i = 1;
		while (i > 0)
			i *= 2;

		return i - 1;
	}

}
