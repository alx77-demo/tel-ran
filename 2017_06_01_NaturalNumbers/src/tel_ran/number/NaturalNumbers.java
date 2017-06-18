package tel_ran.number;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class NaturalNumbers implements Iterable<Integer> {
	ArrayList<List<Integer>> numbers;

	public ArrayList<List<Integer>> getPlist() {
		return numbers;
	}

	int maxNumber;
//	int forNumber;

//	public void setForNumber(int forNumber) {
//		this.forNumber = forNumber;
//	}

	public NaturalNumbers(int maxNumber) {
		numbers = new ArrayList<>(maxNumber);
		for (int i = 1; i <= maxNumber; i++) {
			numbers.add(this.getDividers(i));
		}
		this.maxNumber = maxNumber;
	}

	public int[] getPrimes() {
		List<Integer> resList = new ArrayList<>();
		int size = numbers.size();
		for (int i = 0; i < size; i++) {
			if (numbers.get(i).size() == 2)
				resList.add(i + 1);
		}
		// convert
		int[] res = new int[resList.size()];
		int j = 0;
		for (Integer n : resList) {
			res[j++] = n;
		}
		return res;
	}

	public int greatestCommonDivider(int number1, int number2) {
		if (number1 < 0 || number1 > maxNumber || number2 < 0 || number2 > maxNumber)
			throw new NoSuchElementException("Error");
		List<Integer> listNum1 = numbers.get(number1 - 1);
		List<Integer> listNum2 = numbers.get(number1 - 1);
		ListIterator<Integer> it = listNum1.listIterator(/*listNum1.size()*/);
		while (it.hasNext()) {
			int divider = it.next();
			if (listNum2.contains(divider))
				return divider;
		}
		// for (int left : numbers.get(number1)) {
		// for (int right : numbers.get(number2)) {
		// if (left == right)
		// return left;
		// }
		// }
		return 1;
	}

	@Override
	public String toString() {
		return "NaturalNumbers [numbers=" + numbers + ", maxNumber=" + maxNumber + "]";
	}

	private List<Integer> getDividers(int number) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = number; i >= 1; i--) {
			if (number % i == 0)
				list.add(i);
		}
		return list;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new NaturalNumberIterator(maxNumber);
	}

}
