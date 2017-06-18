package tel_ran.numbers;

public class PrimesNumberRule implements Rule {

	private int divider;

	@Override
	public boolean match(int number) throws RuleViolationException {
		int rem = number % divider;
		int fixingNumber = 0;
		if (rem != 0) {
			if (number < divider)
				fixingNumber = divider - number;
			fixingNumber = rem > divider / 2 ? divider - rem : -rem;
			throw new RuleViolationException(fixingNumber);
		}
		return false;
	}

	private int getFixingNumber(int number) {
		int count = 0;
		while (true) {
			int prime = number - count;
			if (isPrimeNumber(prime)) {
				return -count;
			}
			prime = number + count;
			if (isPrimeNumber(prime)) {
				return count;
			}
			count++;
		}
	}

	private boolean isPrimeNumber(int prime) {
		return false;
	}
}
