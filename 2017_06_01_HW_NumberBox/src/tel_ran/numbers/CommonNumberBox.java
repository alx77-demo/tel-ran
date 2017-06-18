package tel_ran.numbers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public abstract class CommonNumberBox implements NumberBox {
	Collection<Integer> box;

	@Override
	public Iterator<Integer> iterator() {
		return box.iterator();
	}

	@Override
	public void addNumber(int number) {
		box.add(number);
	}

	@Override
	public void removeNumber(int number) {
		box.remove((Integer) number);
	}

	@Override
	public int[] getEvenNumbers() {
		List<Integer> resList = new ArrayList<>();
		for (Integer i : box) {
			if (i % 2 == 0)
				resList.add(i);
		}
		int[] res = convert(resList);

		return res;
	}

	protected int[] convert(Collection<Integer> box) {
		int[] res = new int[box.size()];
		int j = 0;
		for (Integer n : box) {
			res[j++] = n;
		}
		return res;
	}

	public int[] getArray() {
		return convert(box);
	}

	@Override
	public void removeOddNumbers() {
		Iterator<Integer> it = box.iterator();
		while (it.hasNext())
			if (it.next() % 2 != 0)
				it.remove();
	}

	@Override
	public void removeRepeated() {
		Collection<Integer> helper = new HashSet<Integer>();
		Iterator<Integer> it = box.iterator();
		Integer num;
		while (it.hasNext()) {
			num = it.next();
			if (helper.contains(num))
				it.remove();
			helper.add(num);
		}
	}

	abstract protected int[] runThrough(Predicate<Integer> p);

	@Override
	public int[] getDividerNumbers(int divider) {
		return runThrough(new Predicate<Integer>() {

			@Override
			public boolean test(Integer t) {
				return t % divider == 0;
			}
		});
	}

	@Override
	public int[] getNumbersInRange(int minInclusive, int maxExclusive) {
		return runThrough(new Predicate<Integer>() {

			@Override
			public boolean test(Integer t) {
				return t >= minInclusive && t < maxExclusive;
			}
		});
	}

}
