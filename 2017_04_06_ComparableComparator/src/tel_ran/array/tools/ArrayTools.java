package tel_ran.array.tools;

public class ArrayTools {
	public static void fillArray(int[] ar) {
		for (int i = 0; i < ar.length; i++) {
			ar[i] = i;
		}
	}

	public static void fillArrayRandom(int[] ar, int leftBound, int rightBound) {
		for (int i = 0; i < ar.length; i++) {
			ar[i] = (int) (Math.random() * (rightBound - leftBound)) + leftBound;
		}
	}

	public static void invertSign(int[] ar) {
		for (int i = 0; i < ar.length; i++) {
			ar[i] = -ar[i];
		}
	}

	public static void bubbleSort(IComparable[] ar) {
		IComparable val;
		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar.length - i - 1; j++) {
				if (ar[j + 1].compare(ar[j])>0) {
					val = ar[j];
					ar[j] = ar[j + 1];
					ar[j + 1] = val;
				}
			}
		}
	}

	// return index of the item or -1 if item not found
	public static int binarySearch(int[] haystack, int needle) {
		int left = 0, right = haystack.length, center;
		while (left != right) {
			center = (left + right) / 2;
			if (haystack[center] == needle) {
				return center;
			} else if (haystack[center] < needle) {
				left = center;
			} else {
				right = center;
			}
			if (right - left == 1 && haystack[left] != needle && haystack[right] != needle)
				return -1;
		}
		return -1;
	}

	public static void printArray(Object[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
