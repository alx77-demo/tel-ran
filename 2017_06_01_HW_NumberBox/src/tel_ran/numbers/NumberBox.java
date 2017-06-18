package tel_ran.numbers;

public interface NumberBox extends Iterable<Integer> {
	void addNumber(int number);

	void removeNumber(int number);

	int[] getEvenNumbers();

	// return numbers that are divided by the specific divider
	int[] getDividerNumbers(int divider);

	int[] getNumbersInRange(int minInclusive, int maxExclusive);

	void removeOddNumbers();

	public int[] getArray();

	// box contains: 2 6 -10 7 2 -10
	// after method: 2 6 -10 7
	void removeRepeated();

	// два вида хранения этого - LinkedList & ArrayList (DRY)
}
