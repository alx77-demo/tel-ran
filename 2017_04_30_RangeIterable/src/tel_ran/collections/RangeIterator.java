package tel_ran.collections;

import java.util.Iterator;

public class RangeIterator implements Iterator<Integer> {
	Range self;
	int pointer;

	public RangeIterator(Range self) {
		this.self = self;
		pointer = self.getLower();
	}

	@Override
	public boolean hasNext() {
		return pointer <= self.getHigher();
	}

	@Override
	public Integer next() {
		return pointer++;
	}

}
