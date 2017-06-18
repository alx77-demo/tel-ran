package tel_ran.number;

import java.util.Iterator;

public class NaturalNumberIterator implements Iterator<Integer> {

	private int current = 1;
	private int maxNumber;

	public NaturalNumberIterator(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	// lo ihpat - всё равно

	@Override
	public boolean hasNext() {
		return current <= maxNumber;
	}

	@Override
	public Integer next() {
		return current++;
	}

};
