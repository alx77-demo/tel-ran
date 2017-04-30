package tel_ran.collections;

import java.util.Iterator;

public class Range implements Iterable<Integer> {
	Integer lower, higher;

	public Range(Integer lower, Integer higher)
	{
		this.lower = lower;
		this.higher = higher;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator(this);
	}

	public Integer getLower() {
		return lower;
	}

	public void setLower(Integer lower) {
		this.lower = lower;
	}

	public Integer getHigher() {
		return higher;
	}

	public void setHigher(Integer higher) {
		this.higher = higher;
	}

}
