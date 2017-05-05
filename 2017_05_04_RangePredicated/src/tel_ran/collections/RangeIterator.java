package tel_ran.collections;

import java.util.Iterator;

public class RangeIterator implements Iterator<Integer> {
	RangePredicated self;
	int pointer;

	public RangeIterator(RangePredicated self) {
		this.self = self;
		pointer = self.getLower();
	}

	@Override
	public boolean hasNext() {
		int tmpPointer = pointer;
		while (tmpPointer <= self.getHigher()) {
			if (self.getPred().test(tmpPointer))
			{
				tmpPointer++;
				return true;
			}
			tmpPointer++;
		}
		return false;
	}

	@Override
	public Integer next() {
		while (pointer <= self.getHigher()) {
			if (self.getPred().test(pointer)) {
				return pointer++;
			}
			pointer++;
		}
		return -1;
	}

}
