package tel_ran.collections;

import java.util.Iterator;
import java.util.function.Predicate;

public class RangePredicated implements Iterable<Integer> {
	Integer lower, higher;
	Predicate<Integer> pred;

	public RangePredicated(Integer lower, Integer higher, Predicate<Integer> pred) {
		this.lower = lower;
		this.higher = higher;
		this.pred = pred;
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

	public Predicate<Integer> getPred() {
		return pred;
	}

	public void setPred(Predicate<Integer> pred) {
		this.pred = pred;
	}

}
